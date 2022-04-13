package knz.mathknigt.restApi.identyfication;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.Map;

@Getter
@AllArgsConstructor
public class JWTPayload {

    @NonNull final Long     id_user;
    @NonNull final String   email;

    @Nullable
    public static JWTPayload valueOf(@NonNull String jwt, @NonNull String secret) {
        try {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
            return new JWTPayload(
                    body.get("id_user", Long.class),
                    body.get("email", String.class)
            );
        } catch (ExpiredJwtException expEx) {
            System.out.println("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            System.out.println("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            System.out.println("Malformed jwt");
        } catch (SignatureException sEx) {
            System.out.println("Invalid signature");
        } catch (Exception e) {
            System.out.println("invalid token");
        }
        return null;
    }

    @Nullable
    public String toJWT(@NonNull String secret) {
        return Jwts.builder().
                addClaims(Map.of(
                        "id_user", this.id_user,
                        "email", this.email
                )).
                signWith(SignatureAlgorithm.HS512, secret).
                compact();
    }

    @Override
    public String toString() {
        return "JWTPayload{" + "id_user=" + this.id_user + ", email=" + this.email + '}';
    }
}
