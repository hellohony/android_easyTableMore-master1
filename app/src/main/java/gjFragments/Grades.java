package gjFragments;

public class Grades {
    private String math;
    private String english;
    private String chinese;
    public Grades() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Grades(String math, String english, String chinese) {
        super();
        this.math = math;
        this.english = english;
        this.chinese = chinese;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chinese == null) ? 0 : chinese.hashCode());
        result = prime * result + ((english == null) ? 0 : english.hashCode());
        result = prime * result + ((math == null) ? 0 : math.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Grades other = (Grades) obj;
        if (chinese == null) {
            if (other.chinese != null)
                return false;
        } else if (!chinese.equals(other.chinese))
            return false;
        if (english == null) {
            if (other.english != null)
                return false;
        } else if (!english.equals(other.english))
            return false;
        if (math == null) {
            if (other.math != null)
                return false;
        } else if (!math.equals(other.math))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Grades [math=" + math + ", english=" + english + ", chinese=" + chinese + "]";
    }
    public String getMath() {
        return math;
    }
    public void setMath(String math) {
        this.math = math;
    }
    public String getEnglish() {
        return english;
    }
    public void setEnglish(String english) {
        this.english = english;
    }
    public String getChinese() {
        return chinese;
    }
    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
