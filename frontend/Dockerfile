FROM oven/bun:latest

# copy dist
RUN mkdir /app
COPY build /app

# copy modules
COPY node_modules /app/node_modules

WORKDIR /app
ENTRYPOINT [ "bun", "index.js"]