// arrayUtil.js
/**
 * @description 安全的获取数组对应下标数据
 * @param { Array } arr
 * @param { int } index
 */
export const saveGet = (arr, index) => {
  return arr[index];
  // if( arr & Array.isArray(arr)) {
  //   return arr[index];
  // } else {
  //   return undefined;
  // }
}