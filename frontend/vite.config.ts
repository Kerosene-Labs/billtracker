import { sveltekit } from "@sveltejs/kit/vite";
import { defineConfig } from "vite";

export default defineConfig({
  plugins: [sveltekit()],
  server: {
    host: "0.0.0.0",
    proxy: {
      "/api/v1": {
        target: "http://localhost:8080",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api\/v1/, ""), // Remove /api prefix
      },
    },
  },
});
