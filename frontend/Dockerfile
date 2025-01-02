FROM node:lts

# copy dist
RUN mkdir /app
COPY build /app

# copy modules
COPY node_modules /app/node_modules

WORKDIR /app
ENTRYPOINT [ "node", "index.js"]