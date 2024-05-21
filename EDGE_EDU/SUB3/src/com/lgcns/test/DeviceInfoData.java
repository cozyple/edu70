package com.lgcns.test;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DeviceInfoData {
	@SerializedName("deviceInfo")
	private List<DeviceInfo> deviceInfoList;

	public List<DeviceInfo> getDeviceInfoList() {
		return deviceInfoList;
	}

	public class DeviceInfo {
		private String device;
		private String hostname;
		private int port;

		public String getDevice() {
			return device;
		}

		public String getHostname() {
			return hostname;
		}

		public int getPort() {
			return port;
		}
	}
}
