const dev = {
  url: "http://localhost:8080/springbootshoeapi"  // when running backend locally
};

const prod = {
  url: "http://localhost:2030/springbootshoeapi"  // when deployed to Tomcat
};

const config = process.env.NODE_ENV === "production" ? prod : dev;

export default config;
