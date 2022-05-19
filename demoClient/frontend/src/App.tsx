import { useEffect, useState } from 'react';
import './App.css';
import { request } from './http';
import QrReader from 'react-qr-reader';
import { Grid, TextField } from '@mui/material';

interface RedirectShare {
  secret: string
  url: string
}

interface InputSecret{
  secret : string
}

function App() {
  const [text, setText] = useState<string>("QR Code");
  
  const updateShare = async (inputSecret : InputSecret) => {
    
    var redirectShare : RedirectShare = {
        secret: inputSecret.secret,
        url : "https://www.google.de/search?q=" + encodeURIComponent(text)
    };
    //alert(JSON.stringify(redirectShare))
    await request<RedirectShare>('/api/share', { 
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }, 
      body: JSON.stringify(redirectShare)
    }); 
  }

  return (
  <div className="App">

    <Grid
      container
      spacing={0}
      direction="column"
      alignItems="center"
      justifyContent="center"
      style={{ minHeight: '20vh' }} >
      <Grid item xs={3}> 

        <TextField sx={{marginTop:"20px"}} id="standard-basic" label="Enter Google Search" variant="standard" value={text} onChange={(e)=> setText(e.target.value)}/>

        <h1>Scan with:</h1>

        <QrReader 
          style={{width: "300px"}}  
            delay={100}       
            onScan={(data) =>{
              if(data){
                var inputSecret : InputSecret = JSON.parse(data);
                if(inputSecret && inputSecret.secret){
                    updateShare(inputSecret);
                }
              }
            }}
            onError={(err) => alert(err)}
          />        
      </Grid>
    </Grid>        
  </div>
  );
}

export default App;
