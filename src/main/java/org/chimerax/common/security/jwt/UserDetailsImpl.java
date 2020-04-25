package org.chimerax.common.security.jwt;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 25-Apr-20
 * Time: 12:03 AM
 */

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;

    @Singular
    private List<GrantedAuthority> authorities;

    @Builder.Default
    private boolean accountNonExpired = true;

    @Builder.Default
    private boolean accountNonLocked = true;

    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Builder.Default
    private boolean enabled = true;
}
