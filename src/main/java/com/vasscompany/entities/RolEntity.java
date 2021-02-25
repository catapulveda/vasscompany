package com.vasscompany.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rol")
public class RolEntity extends BaseIdEntity implements Serializable {

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "permission_role",
        joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "role_id_fk"))
        },
        inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "permission_id_fk"))
        }
    )
    private List<PermissionEntity> permissionEntities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionEntity> getPermissionEntities() {
        return permissionEntities;
    }

    public void setPermissionEntities(List<PermissionEntity> permissionEntities) {
        this.permissionEntities = permissionEntities;
    }

}
