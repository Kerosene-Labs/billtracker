import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.kerosenelabs.billtracker',
  appName: 'BillTracker',
  webDir: 'build',
  bundledWebRuntime: false
  // server: {
  //   url: 'https://billtracker.kerosenelabs.com',
  //   cleartext: true,
  //   androidScheme: "https"
  // }
};

export default config;
