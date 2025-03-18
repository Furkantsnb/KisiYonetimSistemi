package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Tüm endpoint'lere CORS uyguluyoruz
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:5500") // İzin verilen kaynaklar
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İzin verilen HTTP metotları
                .allowedHeaders("*") // İzin verilen başlıklar
                .allowCredentials(true) // Kimlik bilgilerine izin veriyoruz
                .maxAge(3600); // Ön kontrol (pre-flight) isteğinin önbellekte tutulma süresi (saniye)
    }
}