package com.example.task1.repository;

import com.example.task1.enity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findByTelNumber(String telNumber);

    @Transactional
    @Modifying
    @Query(value = "update files  set name = :name," +
            "department = :department," +
            "inn = :inn where id = :id", nativeQuery = true)
    void updateFile(@Param("name") String name,
                    @Param("department") String department,
                    @Param("inn") String inn,
                    @Param("id") Long id);
}
