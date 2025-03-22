package org.example.order.web.repository;


import org.example.order.web.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

    Optional<Attachment> findByFileNameAndDeletedFalse(String fileName);

//    <T> ScopedValue<T> findById(List<Long> longs);
}
