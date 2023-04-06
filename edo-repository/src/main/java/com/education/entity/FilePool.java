package com.education.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author Nadezhda Pupina
 * Class FilePool отвечает за обращения граждан, расширяет сущность BaseEntity
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "file_pool")
public class FilePool extends BaseEntity {

    /**
     * storageFileId - Key for getting a file from storage
     */
    @Column(name = "storage_file_id")
    private UUID storageFileId;

    /**
     * name - file name
     */
    @Column(name = "name")
    private String name;

    /**
     * extension - decryption will come later !!
     */
    @Column(name = "extension")
    private String extension;

    /**
     * size - file size
     */
    @Column(name = "size")
    private Long size;

    /**
     * pageCount - number of pages in file
     */
    @Column(name = "page_count")
    private Long pageCount;

    /**
     * uploadDate - file upload date
     */
    @Column(name = "upload_date")
    private ZonedDateTime uploadDate;

    /**
     * archivedDate - file archive date
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * creator - employee who created the file
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private Employee creator;

}