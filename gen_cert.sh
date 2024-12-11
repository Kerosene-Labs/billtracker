#!/bin/bash
openssl genrsa -out dev-key.pem 2048
openssl req -x509 -new -nodes -key dev-key.pem -sha256 -days 365 -out dev-cert.pem \
    -subj "/C=US/ST=State/L=City/O=Organization/OU=Department/CN=localhost"
openssl pkcs12 -export -out dev-cert.p12 -inkey dev-key.pem -in dev-cert.pem -name "DevCert"