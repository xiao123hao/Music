var requestContextPath ="/WorkingSys_Web_exploded";
//获取url中的参数方法
function getQueryParam(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
//根据时间戳转换日期的方法 YYYY-MM-DD格式
function convertSecondsToDate(seconds) {
    // 使用Date对象
    const date = new Date(seconds);
    // 格式化日期（可选，这里以YYYY-MM-DD HH:mm:ss为例）
    const formattedDate = date.getFullYear() + '-' +
        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
        ('0' + date.getDate()).slice(-2);
    return formattedDate;
}
//判断用户输入的日期字符串是否为  YYYY-MM-DD的正确日期数据
function isValidDateFormatStrict(dateString) {
    const regex = /^(\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/;
    if (!regex.test(dateString)) {
        return false;
    }

    const [year, month, day] = dateString.split('-').map(Number);
    const date = new Date(year, month - 1, day); // 注意月份是从0开始的

    // 检查日期是否有效
    // 如果月份或日期溢出，JavaScript会自动调整，因此我们需要检查调整后的日期是否与原字符串一致
    return date.getFullYear() === year && date.getMonth() + 1 === month && date.getDate() === day;
}