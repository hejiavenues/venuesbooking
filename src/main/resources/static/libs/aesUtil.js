/**
 * 加密（需要先加载lib/aes/aes.min.js文件）
 * @param word
 * @returns {*}
 */
var iv = CryptoJS.enc.Utf8.parse('e4b9ba1c7bf76ceb');   //十六位十六进制数作为密钥偏移量

function encrypt(word){
    var key = CryptoJS.enc.Utf8.parse("abcdnnnnnn123456");
    var srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {iv: iv,mode:CryptoJS.mode.CBC,padding: CryptoJS.pad.Pkcs7});
    return encrypted.toString();
}

/**
 * 解密
 * @param word
 * @returns {*}
 */
function decrypt(word){
    var key = CryptoJS.enc.Utf8.parse("abcdnnnnnn123456");
    var decrypt = CryptoJS.AES.decrypt(word, key, {iv: iv,mode:CryptoJS.mode.CBC,padding: CryptoJS.pad.Pkcs7});
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}