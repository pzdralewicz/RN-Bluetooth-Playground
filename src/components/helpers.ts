import {PermissionsAndroid, PermissionStatus, Platform} from 'react-native';

export const hasBTPermissions = (): Promise<boolean> => {
  return new Promise((resolve, reject) => {
    if (Platform.OS === 'android' && Platform.Version >= 23) {
      PermissionsAndroid.check(
        PermissionsAndroid.PERMISSIONS.ACCESS_COARSE_LOCATION,
      )
        .then((result) => resolve(result))
        .catch(() => reject());
    }
  });
};

export const requestBTPermissions = (): Promise<PermissionStatus> => {
  return new Promise((resolve, reject) => {
    if (Platform.OS === 'android' && Platform.Version >= 23) {
      PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_COARSE_LOCATION,
      )
        .then((result) => resolve(result))
        .catch(() => reject());
    }
  });
};
