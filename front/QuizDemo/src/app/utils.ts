export default class Utils {
    aaa;
    static pad(d) {
        return (d < 10) ? '0' + d.toString() : d.toString();
    }
}