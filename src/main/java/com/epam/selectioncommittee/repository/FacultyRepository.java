package com.epam.selectioncommittee.repository;

import com.epam.selectioncommittee.model.Faculty;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>, PagingAndSortingRepository<Faculty, Long> {
    @NonNull
    Optional<Faculty> findById(@NonNull Long id);

    @NonNull
    Page<Faculty> findAll(@NonNull Pageable pageable);
}
