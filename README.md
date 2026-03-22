# Server Opt Visualizer

예약/예매 플랫폼을 소재로 서버 병목과 성능 개선을 비교하는 모놀리스 실험 프로젝트입니다.
핵심은 최소 기능으로 병목을 재현하고, 개선 전/후를 설명 가능한 형태로 남기는 것입니다.

자세한 기획, 범위, 모델링 문서화 방향은 [PROJECT.md](PROJECT.md)에서 관리합니다.

## Summary

- Spring Boot App
- PostgreSQL
- Redis
- Flyway
- Thymeleaf

## Run

로컬 인프라 실행:

```bash
docker compose up -d
```

애플리케이션 실행:

```bash
./gradlew bootRun
```

테스트 실행:

```bash
./gradlew test
```

인프라 중지:

```bash
docker compose down
```

데이터까지 초기화:

```bash
docker compose down -v
```

## Docs

- [README.md](README.md): 프로젝트 요약과 실행 방법
- [PROJECT.md](PROJECT.md): 기획, 범위, 실험 목표, MDD 방향

## Notes

- 기본 실행 흐름은 Docker Compose로 PostgreSQL, Redis를 띄운 뒤 애플리케이션을 시작하는 방식입니다.
- 모델링 문서는 코드 변경과 함께 갱신하는 것을 원칙으로 합니다.
- Kafka와 Grafana는 핵심 흐름 안정화 이후 검토할 후속 실험 후보입니다.
