package com.project.boardadmin.domain;

import com.project.boardadmin.domain.constant.RoleType;
import com.project.boardadmin.domain.converter.RoleTypesConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class AdminAccount extends AuditingFields {
    @Id
    @Column(length = 50)
    private String userId;

    @Setter @Column(nullable = false) private String userPassword;

    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false)
    private Set<RoleType> roleTypes = new LinkedHashSet<>();

    @Setter @Column(length = 100) private String email;
    @Setter @Column(length = 100) private String nickname;
    @Setter private String memo;


    protected AdminAccount() {}

    private AdminAccount(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.roleTypes = roleTypes;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
        this.createdBy = createdBy;
    }

    public static AdminAccount of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo) {
        return AdminAccount.of(userId, userPassword, roleTypes, email, nickname, memo,null);
    }

    public static AdminAccount of(String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, String memo, String createdBy) {
        return new AdminAccount(userId, userPassword, roleTypes, email, nickname, memo, createdBy);
    }

    public void addRoleType(RoleType roleType) {
        this.roleTypes.add(roleType);
    }

    public void addRoleTypes(Collection<RoleType> roleTypes) {
        this.roleTypes.addAll(roleTypes);
    }

    public void removeRoleType(RoleType roleType) {
        this.roleTypes.remove(roleType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminAccount that)) return false;
        return userId != null && userId.equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }

}