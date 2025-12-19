package Demo.JWT;

import Demo.Enity.Accounts;
import Demo.Service.AccountSecurityService;
import Demo.Service.AccountsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    @Autowired
    private AccountSecurityService accountSecurityService;
    private static final String KEY_SECRET="MTIzNDU2NDU5OThEMzIxM0F6eGMzNTE2NTQzMjEzMjE2NTQ5OHEzMTNhMnMxZDMyMnp4M2MyMQ==";
    // Tạo jwt (tạo thông tin trả về FE khi thành công )
    public String genarateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        Accounts accounts = accountSecurityService.findByUsername(username);
        claims.put("accountsId",accounts.getAccountsId());
        claims.put("usersId",accounts.getUsers().getUsersId());
        claims.put("usersUsersName",accounts.getUsers().getUsersName());
        claims.put("usersStatus",accounts.getUsers().getStatus());
        claims.put("usersRolesName",accounts.getUsers().getRoles().getRoleName());
        return createToken(claims,username);
    }
    // Tạo JWT với những claims đã chọn
    public String createToken(Map<String,Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+100000L*60*60*1000))
                .signWith(SignatureAlgorithm.HS256,getSigneKey())
                .compact();
    }
    // Lấy key_secret
    private Key getSigneKey(){
        byte[] keyByte = Decoders.BASE64.decode(KEY_SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }
    // Trich xuất thông tin (Lấy ra các thông số)
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getSigneKey()).parseClaimsJws(token).getBody();
    }
    // Trich xuất thông tin cụ thể từ thông tin tổng quát của token
    public <T> T extractClaim(String token, Function<Claims,T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    // Lấy ra thời gian hết hạn
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    // Lấy ra username
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    // Kiểm tra xem token đó đã hết hạn hay chưa
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Kiểm tra tính hợp lệ của token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
