const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/questions',
    createProxyMiddleware({
      target: 'http://3.39.150.26:8080',
      changeOrigin: true,
    })
  );
  app.use(
    '/members',
    createProxyMiddleware({
      target: 'http://3.39.150.26:8080',
      changeOrigin: true,
    })
  );
  app.use(
    '/answers',
    createProxyMiddleware({
      target: 'http://3.39.150.26:8080',
      changeOrigin: true,
    })
  );
};