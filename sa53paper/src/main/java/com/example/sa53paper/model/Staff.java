package com.example.sa53paper.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Staff extends User {
    @OneToMany
    @JoinTable(name="staff_artefact", joinColumns = @JoinColumn(name="uid"),
            inverseJoinColumns = @JoinColumn(name="aid"))
    private List<Artefact> arte;

    public Staff(Integer uid, String username) {
        super(uid, username);
    }
}
