package com.crossshare.server.entities.repositories;

import com.crossshare.server.entities.models.Share;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends PagingAndSortingRepository<Share, Long> {
    List<Share> findBySecret(String secret);
}
