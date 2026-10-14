// vite.config.ts
import { defineConfig, loadEnv } from "file:///workspace/scratch/bebb73d2dccc/work/admin/rentHouseAdmin/node_modules/vite/dist/node/index.js";
import vue from "file:///workspace/scratch/bebb73d2dccc/work/admin/rentHouseAdmin/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import path from "path";
import { viteMockServe } from "file:///workspace/scratch/bebb73d2dccc/work/admin/rentHouseAdmin/node_modules/vite-plugin-mock/dist/index.js";
import { createSvgIconsPlugin } from "file:///workspace/scratch/bebb73d2dccc/work/admin/rentHouseAdmin/node_modules/vite-plugin-svg-icons/dist/index.mjs";
import VueSetupExtend from "file:///workspace/scratch/bebb73d2dccc/work/admin/rentHouseAdmin/node_modules/vite-plugin-vue-setup-extend/dist/index.mjs";
import vueJsx from "file:///workspace/scratch/bebb73d2dccc/work/admin/rentHouseAdmin/node_modules/@vitejs/plugin-vue-jsx/dist/index.mjs";
import svgLoader from "file:///workspace/scratch/bebb73d2dccc/work/admin/rentHouseAdmin/node_modules/vite-svg-loader/index.js";
var __vite_injected_original_dirname = "/workspace/scratch/bebb73d2dccc/work/admin/rentHouseAdmin";
var vite_config_default = defineConfig((config) => {
  const { command, mode } = config;
  const env = loadEnv(mode, process.cwd());
  return {
    base: "/",
    plugins: [
      vue(),
      viteMockServe({
        // 只在开发阶段开启 mock 服务,mock和后端服务器接口能共存，可以通过配置来区分
        localEnabled: command === "serve"
      }),
      createSvgIconsPlugin({
        iconDirs: [path.resolve(process.cwd(), "src/assets/icons")],
        symbolId: "icon-[dir]-[name]"
      }),
      // * vite 可以使用 jsx/tsx 语法
      vueJsx(),
      // * name 可以写在 script 标签上
      VueSetupExtend(),
      // * vite-svg-loader支持，可以直接引入svg图标当作组件使用
      svgLoader()
    ],
    resolve: {
      alias: {
        "@": path.resolve(__vite_injected_original_dirname, "src")
      }
    },
    css: {
      preprocessorOptions: {
        scss: {
          javascriptEnabled: true,
          additionalData: '@import "./src/styles/variable.scss";'
        }
      }
    },
    server: {
      host: "localhost",
      proxy: {
        "/admin": {
          target: env.VITE_APP_BASE_URL,
          changeOrigin: true
        }
      }
    }
  };
});
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCIvd29ya3NwYWNlL3NjcmF0Y2gvYmViYjczZDJkY2NjL3dvcmsvYWRtaW4vcmVudEhvdXNlQWRtaW5cIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZmlsZW5hbWUgPSBcIi93b3Jrc3BhY2Uvc2NyYXRjaC9iZWJiNzNkMmRjY2Mvd29yay9hZG1pbi9yZW50SG91c2VBZG1pbi92aXRlLmNvbmZpZy50c1wiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9pbXBvcnRfbWV0YV91cmwgPSBcImZpbGU6Ly8vd29ya3NwYWNlL3NjcmF0Y2gvYmViYjczZDJkY2NjL3dvcmsvYWRtaW4vcmVudEhvdXNlQWRtaW4vdml0ZS5jb25maWcudHNcIjtpbXBvcnQgeyBkZWZpbmVDb25maWcsIGxvYWRFbnYgfSBmcm9tICd2aXRlJ1xuaW1wb3J0IHZ1ZSBmcm9tICdAdml0ZWpzL3BsdWdpbi12dWUnXG5pbXBvcnQgcGF0aCBmcm9tICdwYXRoJ1xuaW1wb3J0IHsgdml0ZU1vY2tTZXJ2ZSB9IGZyb20gJ3ZpdGUtcGx1Z2luLW1vY2snXG5pbXBvcnQgeyBjcmVhdGVTdmdJY29uc1BsdWdpbiB9IGZyb20gJ3ZpdGUtcGx1Z2luLXN2Zy1pY29ucydcbmltcG9ydCBWdWVTZXR1cEV4dGVuZCBmcm9tICd2aXRlLXBsdWdpbi12dWUtc2V0dXAtZXh0ZW5kJ1xuaW1wb3J0IHZ1ZUpzeCBmcm9tICdAdml0ZWpzL3BsdWdpbi12dWUtanN4J1xuaW1wb3J0IHN2Z0xvYWRlciBmcm9tICd2aXRlLXN2Zy1sb2FkZXInXG5cbmV4cG9ydCBkZWZhdWx0IGRlZmluZUNvbmZpZygoY29uZmlnKSA9PiB7XG4gIGNvbnN0IHsgY29tbWFuZCwgbW9kZSB9ID0gY29uZmlnXG4gIGNvbnN0IGVudiA9IGxvYWRFbnYobW9kZSwgcHJvY2Vzcy5jd2QoKSlcblxuICByZXR1cm4ge1xuICAgIGJhc2U6ICcvJyxcbiAgICBwbHVnaW5zOiBbXG4gICAgICB2dWUoKSxcbiAgICAgIHZpdGVNb2NrU2VydmUoe1xuICAgICAgICAvLyBcdTUzRUFcdTU3MjhcdTVGMDBcdTUzRDFcdTk2MzZcdTZCQjVcdTVGMDBcdTU0MkYgbW9jayBcdTY3MERcdTUyQTEsbW9ja1x1NTQ4Q1x1NTQwRVx1N0FFRlx1NjcwRFx1NTJBMVx1NTY2OFx1NjNBNVx1NTNFM1x1ODBGRFx1NTE3MVx1NUI1OFx1RkYwQ1x1NTNFRlx1NEVFNVx1OTAxQVx1OEZDN1x1OTE0RFx1N0Y2RVx1Njc2NVx1NTMzQVx1NTIwNlxuICAgICAgICBsb2NhbEVuYWJsZWQ6IGNvbW1hbmQgPT09ICdzZXJ2ZScsXG4gICAgICB9KSxcbiAgICAgIGNyZWF0ZVN2Z0ljb25zUGx1Z2luKHtcbiAgICAgICAgaWNvbkRpcnM6IFtwYXRoLnJlc29sdmUocHJvY2Vzcy5jd2QoKSwgJ3NyYy9hc3NldHMvaWNvbnMnKV0sXG4gICAgICAgIHN5bWJvbElkOiAnaWNvbi1bZGlyXS1bbmFtZV0nLFxuICAgICAgfSksXG4gICAgICAvLyAqIHZpdGUgXHU1M0VGXHU0RUU1XHU0RjdGXHU3NTI4IGpzeC90c3ggXHU4QkVEXHU2Q0Q1XG4gICAgICB2dWVKc3goKSxcbiAgICAgIC8vICogbmFtZSBcdTUzRUZcdTRFRTVcdTUxOTlcdTU3Mjggc2NyaXB0IFx1NjgwN1x1N0I3RVx1NEUwQVxuICAgICAgVnVlU2V0dXBFeHRlbmQoKSxcbiAgICAgIC8vICogdml0ZS1zdmctbG9hZGVyXHU2NTJGXHU2MzAxXHVGRjBDXHU1M0VGXHU0RUU1XHU3NkY0XHU2M0E1XHU1RjE1XHU1MTY1c3ZnXHU1NkZFXHU2ODA3XHU1RjUzXHU0RjVDXHU3RUM0XHU0RUY2XHU0RjdGXHU3NTI4XG4gICAgICBzdmdMb2FkZXIoKSxcbiAgICBdLFxuICAgIHJlc29sdmU6IHtcbiAgICAgIGFsaWFzOiB7XG4gICAgICAgICdAJzogcGF0aC5yZXNvbHZlKF9fZGlybmFtZSwgJ3NyYycpLFxuICAgICAgfSxcbiAgICB9LFxuICAgIGNzczoge1xuICAgICAgcHJlcHJvY2Vzc29yT3B0aW9uczoge1xuICAgICAgICBzY3NzOiB7XG4gICAgICAgICAgamF2YXNjcmlwdEVuYWJsZWQ6IHRydWUsXG4gICAgICAgICAgYWRkaXRpb25hbERhdGE6ICdAaW1wb3J0IFwiLi9zcmMvc3R5bGVzL3ZhcmlhYmxlLnNjc3NcIjsnLFxuICAgICAgICB9LFxuICAgICAgfSxcbiAgICB9LFxuICAgIHNlcnZlcjoge1xuICAgICAgaG9zdDogJ2xvY2FsaG9zdCcsXG4gICAgICBwcm94eToge1xuICAgICAgICAnL2FkbWluJzoge1xuICAgICAgICAgIHRhcmdldDogZW52LlZJVEVfQVBQX0JBU0VfVVJMLFxuICAgICAgICAgIGNoYW5nZU9yaWdpbjogdHJ1ZSxcbiAgICAgICAgfSxcbiAgICAgIH0sXG4gICAgfSxcbiAgfVxufSlcbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBNlYsU0FBUyxjQUFjLGVBQWU7QUFDblksT0FBTyxTQUFTO0FBQ2hCLE9BQU8sVUFBVTtBQUNqQixTQUFTLHFCQUFxQjtBQUM5QixTQUFTLDRCQUE0QjtBQUNyQyxPQUFPLG9CQUFvQjtBQUMzQixPQUFPLFlBQVk7QUFDbkIsT0FBTyxlQUFlO0FBUHRCLElBQU0sbUNBQW1DO0FBU3pDLElBQU8sc0JBQVEsYUFBYSxDQUFDLFdBQVc7QUFDdEMsUUFBTSxFQUFFLFNBQVMsS0FBSyxJQUFJO0FBQzFCLFFBQU0sTUFBTSxRQUFRLE1BQU0sUUFBUSxJQUFJLENBQUM7QUFFdkMsU0FBTztBQUFBLElBQ0wsTUFBTTtBQUFBLElBQ04sU0FBUztBQUFBLE1BQ1AsSUFBSTtBQUFBLE1BQ0osY0FBYztBQUFBO0FBQUEsUUFFWixjQUFjLFlBQVk7QUFBQSxNQUM1QixDQUFDO0FBQUEsTUFDRCxxQkFBcUI7QUFBQSxRQUNuQixVQUFVLENBQUMsS0FBSyxRQUFRLFFBQVEsSUFBSSxHQUFHLGtCQUFrQixDQUFDO0FBQUEsUUFDMUQsVUFBVTtBQUFBLE1BQ1osQ0FBQztBQUFBO0FBQUEsTUFFRCxPQUFPO0FBQUE7QUFBQSxNQUVQLGVBQWU7QUFBQTtBQUFBLE1BRWYsVUFBVTtBQUFBLElBQ1o7QUFBQSxJQUNBLFNBQVM7QUFBQSxNQUNQLE9BQU87QUFBQSxRQUNMLEtBQUssS0FBSyxRQUFRLGtDQUFXLEtBQUs7QUFBQSxNQUNwQztBQUFBLElBQ0Y7QUFBQSxJQUNBLEtBQUs7QUFBQSxNQUNILHFCQUFxQjtBQUFBLFFBQ25CLE1BQU07QUFBQSxVQUNKLG1CQUFtQjtBQUFBLFVBQ25CLGdCQUFnQjtBQUFBLFFBQ2xCO0FBQUEsTUFDRjtBQUFBLElBQ0Y7QUFBQSxJQUNBLFFBQVE7QUFBQSxNQUNOLE1BQU07QUFBQSxNQUNOLE9BQU87QUFBQSxRQUNMLFVBQVU7QUFBQSxVQUNSLFFBQVEsSUFBSTtBQUFBLFVBQ1osY0FBYztBQUFBLFFBQ2hCO0FBQUEsTUFDRjtBQUFBLElBQ0Y7QUFBQSxFQUNGO0FBQ0YsQ0FBQzsiLAogICJuYW1lcyI6IFtdCn0K
