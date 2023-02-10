package ru.yumeno.nir.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false)
    @NotBlank(message = "News header cannot be null")
    private String header;
    @Column(length = 3000)
    @Size(max = 3000, message = "News body cannot be greater than 3000 symbols")
    private String body;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;
    @ManyToMany
    @JoinTable(
            name = "news_tag",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_name")
    )
    private List<Tag> tags;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_address",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;

    @PrePersist
    protected void prePersist() {
        createDate = LocalDateTime.now();
    }
}