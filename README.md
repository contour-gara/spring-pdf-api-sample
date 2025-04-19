# spring-pdf-api-sample

## Quick start

```shell
(cd spring-pdf-app-server && ../mvnw clean package -Dmaven.test.skip=true)
docker compose up --build -d
```

## 領収書のダウンロード

アプリ起動後に以下のコマンドでダウンロードできる。

```shell
curl -X POST \
  http://localhost:8080/receipt/sample \
  -H "Content-Type: application/json" \
  -H "Accept: application/pdf" \
  -d '{"recipientName": "sample name", "remarks": "sample"}' \
  --output receipt.pdf
```
