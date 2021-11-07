public class LineObject {
    private int lineNumber;
    private String lineValue;

    public LineObject(int lineNumber, String lineValue) {
        this.lineNumber = lineNumber;
        this.lineValue = lineValue;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLineValue() {
        return this.lineValue;
    }

    public void setLineValue(String lineValue) {
        this.lineValue = lineValue;
    }

    @Override
    public String toString() {
        return "{" +
                "lineNumber=" + lineNumber +
                ", lineValue='" + lineValue + '\'' +
                '}';
    }

    public boolean isGreaterThan(LineObject obj) {
        // check length
        if (this.lineValue.length() > obj.getLineValue().length())
            return true;
        else if (this.lineValue.length() < obj.getLineValue().length())
            return false;
        // compare each char if the length is equal
        else {
            for (int i = 0; i < this.lineValue.length(); i++) {
                if (Character.getNumericValue(this.lineValue.charAt(i)) > Character.getNumericValue(obj.lineValue.charAt(i)))
                    return true;
                else if (Character.getNumericValue(this.lineValue.charAt(i)) < Character.getNumericValue(obj.lineValue.charAt(i)))
                    return false;
            }
            return false;
        }
    }
}
