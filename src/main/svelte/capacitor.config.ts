import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.kerosenelabs.billtracker',
  appName: 'BillTracker',
  webDir: 'build',
  server: {
    // url: 'http://10.0.2.2:5173',
    cleartext: true,
    androidScheme: "https"
  }
};

export default config;
