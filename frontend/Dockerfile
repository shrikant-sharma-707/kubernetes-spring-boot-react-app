# FROM node:latest
# WORKDIR /app
# COPY node_modules node_modules
# COPY package.json package.json
# COPY package-lock.json package-lock.json
# # use RUN npm ci --production
# RUN npm install
# COPY . .
# # EXPOSE 3000 
# CMD [ "npm", "run","start" ]


# build environment
FROM node:latest as build
WORKDIR /app 
COPY package.json package.json 
# use RUN npm ci --production
RUN npm install 
COPY . .
RUN npm run build
#EXPOSE 3000
#CMD [ "npm", "run","start" ]

 #nginx
FROM nginx:latest as prod
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD [ "nginx","-g", "daemon off;" ]

# docker build -t react-app:v1 -f Dockerfile .