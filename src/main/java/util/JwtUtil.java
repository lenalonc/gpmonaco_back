package util;


import com.gpmonaco.entities.Reservation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
    public class JwtUtil {

        @Value("${jwt.secret}")
        private String SECRET_KEY;

        public String generateToken(Reservation reservation) {

            Map<String, Object> claims = new HashMap<>();
            claims.put("reservationId", reservation.getCustomer().getId());
            claims.put("active", true);

            Calendar expirationCalendar = Calendar.getInstance();
            expirationCalendar.set(2024, Calendar.MAY, 25, 0, 0, 0);
            Date expirationDate = expirationCalendar.getTime();

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
        }

    public String makeTokenPassive(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        claims.put("active", false);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean isTokenActive(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("active", Boolean.class);
    }

    }

