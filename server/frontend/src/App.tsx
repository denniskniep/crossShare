import React, { useEffect, useState } from 'react';
import './App.css';
import { QRCodeSVG } from 'qrcode.react';
import { request } from './http';
import { useInterval } from './interval';
import { useNavigate } from "react-router-dom";

interface Share {
  secret: string
  type: string
  sharedObject: any | null
}

interface RedirectShareObject {
  url : string
}

function App() {
  const [share, setShare] = useState<Share|null>(null);
  const navigate = useNavigate();

  const createShare = async () => {
      const result = await request<Share>('/api/share/create', { method: 'POST'});
      setShare(result);
  }

  const findShare = async (secret : string) : Promise<Share | null> => {
    const result = await request<Share>('/api/share/find', { 
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }, 
      body: JSON.stringify({secret: secret})
    });    
    return result;
  }

  const executeShare = (share : Share) => {
    if(share && share.sharedObject){ 
      if(share.type === "REDIRECT" && share.sharedObject){            
          var redirectSharedObject : RedirectShareObject = JSON.parse(share.sharedObject);
          if(redirectSharedObject && redirectSharedObject.url){
            console.log("navigate to", redirectSharedObject.url)    
            //window.location.href = redirectSharedObject.url;
            window.location.replace(redirectSharedObject.url);
          }
      }
    }
  }
  
  useEffect(() => {
    if(!share){
      createShare();
    }
  }, []);

  useInterval(async () => {
    if(share && !share.sharedObject){      
      let foundShare = await findShare(share.secret);
      if(foundShare?.sharedObject){
        console.log("bing bing", foundShare)
        setShare(foundShare);
        executeShare(foundShare)
      }
    }
  }, 1000);

  return (
    <div className="App"> 
      <h1>Scan me:</h1>
      <QRCodeSVG size={350} value={JSON.stringify({secret: share?.secret})} />
    </div>
  );
}

export default App;
