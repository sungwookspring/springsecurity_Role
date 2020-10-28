
# TroubleShooting
## INTELLIJ junit4 에러
* 에러: 테스트 항목이 존재하지 않은 에러
* 해결: INTELLIJ 컴파일 옵션을 Gradle -> INTELLIJ로 변경 
## passwordEncoder 일치 테스트
* 에러: passwordEncoder.encode()함수로 암호화 확인 시 에러 발생
* 해결: passwordEncoder.matches 함수 사용
```java
Assertions.assertThat(passwordEncoder.matches(join_request.getPassword(), find_user.getPassword())).isTrue();
```
## Enum RequestBody
* 출처: https://sejoung.github.io/2019/06/2019-06-05-spring_jackson-enum-serializing-and-deserializer/#%EC%8A%A4%ED%94%84%EB%A7%81-jackson-enum-deserializer
* 에러: json으로 Enum type을 받을 때 null 에러
* 해결: @JsonCreator 어노테이션 추가
```java
@Getter
@Slf4j
public enum UserRole {
    GENERAL(Sets.newHashSet(BOOK_READ)),
    ADMIN(Sets.newHashSet(BOOK_READ , BOOK_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        log.info("getGrantedAuthorities 요청");
        Set<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }

    private static final Map<String, UserRole> stringToEnum =
            Stream.of(values()).collect(toMap(Objects::toString, e -> e));

    @JsonCreator
    public static UserRole fromString(String role){
        log.info("fromString 호출" + role);
        return stringToEnum.get(role);
    }
}
```  
 ## .jar in the classpath, for example jjwt-impl.jar 에러
 * 출처: https://github.com/jwtk/jjwt/issues/573
 * 에러: dependency 에러
 * 해결: jjwt-jackson depdency 추가
 
 ## OncePerRequestFilter Null 오류
 * 에러: OncePerRequestFilter에서 Autowired를 한 클래스를 불러오지 못해 null초기화
 * 해결: OncePerRequestFilter 클래스는 Bean으로 설정
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    ...

    @Bean
    public JwTokenVerifier jwTokenVerifier(){
        return new JwTokenVerifier();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        ...
                .csrf().disable()
                .addFilterBefore(jwTokenVerifier(), UsernamePasswordAuthenticationFilter.class)
        ...
    }
}
```

## 필터추가 후 http 요청 불가
* 상황: security 필터 추가 후 요청이 안되는 상황
* 해결: 제일 마지막에 다음 필터로 넘어가는 filterChain.doFilter(request, response) 호출
```java
public class JwTokenVerifier extends OncePerRequestFilter {
    ...

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ...

        filterChain.doFilter(request, response);
    }
```
 
# 참고자료
* [1] jwtk library: https://github.com/jwtk/jjwt#install-jdk-gradle