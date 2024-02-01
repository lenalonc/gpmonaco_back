package util;


import com.gpmonaco.entities.Reservation;
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
            claims.put("reservationId", reservation.getId());
            claims.put("customerId", reservation.getCustomer());

            Calendar expirationCalendar = Calendar.getInstance();
            expirationCalendar.set(2024, Calendar.MAY, 25, 0, 0, 0);
            Date expirationDate = expirationCalendar.getTime();

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
        }

    }

