package com.tuling.springcloud.stock.niuke;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.util.CollectionUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


public class niuke1Test {
    @Test
    public void dd(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int i = 0;
        Set<Integer> l = new HashSet<Integer>();
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            if(i>0){
                l.add(a);
            }
            i++;
        }
        List<Integer> ll = new ArrayList<Integer>(l);
        ll.sort(Integer::compareTo);
        Iterator iterator = ll.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void ccvv(){
        TreeSet s = new TreeSet();
        s.add(332);
        s.add(22);
        s.add(22);
        s.add(4455);
        s.add(3344);
        s.add(12);
        s.add(12);
        s.add(55555555);
        System.out.println(s);

    }

    @Test
    public void ddd(){
        int s = 180;
        pr(s);
    }
    void pr(int s){
        for(int i=2;i<=s;i++){
            if(s%i==0){
                System.out.println(i+" ");
                pr(s/i);
                break;
            }
        }
    }

    @Test
    public void dggkjlkj(){
        String sss= "9876673";
        String[] strs = sss.split("");
        List<String> l = Arrays.asList(strs);
        LinkedHashSet<String> s = new LinkedHashSet<String>();
        for(String str : l){
            s.add(str);
        }
        String priStr = "";
        for(String pr : s){
            priStr = priStr  + pr;
        }
        System.out.println(priStr);
    }

    @Test
    public void dssdsdsdsds(){
        String s = "831(l)8^$O+3T";
        List<String> l = Arrays.asList(s.split(""));
        if(l.size()< 8){
            System.out.println("NG");
            return;
        }
        int num = 0;
        boolean lowCase = true;
        boolean upCase = true;
        boolean math = true;
        boolean wd = true;
        for(String str : l){
            if(lowCase && Pattern.matches("[a-z]",str)){
                lowCase = false;
                num++;
            }
            if(upCase && Pattern.matches("[A-Z]",str)){
                upCase = false;
                num++;
            }
            if(math && Pattern.matches("[0-9]",str)){
                math = false;
                num++;
            }
            if(wd && Pattern.matches("[^a-zA-Z0-9]",str)){
                wd = false;
                num++;
            }
        }
        if(num<3){
            System.out.println("NG");
            return;
        }
        if(!matchStr(s,0,3)){
            System.out.println("NG");
            return;
        }
        System.out.println("OK");
    }
    public boolean matchStr(String str,int start,int end){
        if(end>str.length()){
            return true;
        }
        if(str.length()<end){
            return true;
        }
        String replaceStr = str.substring(start,end);
        System.out.println(replaceStr);
        String afterStr = str.replace(replaceStr,"");
        boolean b = str.length() - afterStr.length() > end-start;
        if(b){
            return false;
        }
        return matchStr(str,start+1,end+1);
    }

    @Test
    public void dsdsds(){
        List<String> l = new ArrayList();
        l.add("c");
        l.add("g");
        l.add("k");
        l.add("a");
        Object[] objects = l.toArray();
        Arrays.sort(objects);
        System.out.println(Arrays.toString(objects));
        System.out.println("---------------------");
        String s = Integer.toHexString(14);
        System.out.println(s);
        String dd = "abcedf";
        int a = Integer.parseInt("a", 16);
        String s1 = Integer.toBinaryString(a);
        System.out.println(s1);
        StringBuffer sb = new StringBuffer(s1);
        String s2 = sb.reverse().toString();
        int i = Integer.parseInt(s2, 2);
        System.out.println(i);
    }

    @Test
    public void dggggg(){
        String  dd = "$bo*y gi!r#l";
        String result = new String(dd);
        for (int i = 0; i < dd.length(); i++) {
            Character c = dd.charAt(i);
            String s = c.toString();
            if(Pattern.matches("[^A-Za-z]",s)){
                result = result.replace(s," ");
            }
        }
        String pri = "";
        String[] s = result.split(" ");
        System.out.println(Arrays.toString(s));
        for (int i = s.length-1; i >=0 ; i--) {
            String s1 = s[i];
            pri = pri + s1;
            if(i!=0){
                pri+=" ";
            }
        }
        System.out.println(pri);
    }

    @Test
    public void sdsdsd(){
        String dd = "112233";
        int i = dd.indexOf("1");
        System.out.println(i);
    }

    @Test
    public void dsdssds(){
        String dz  = "10.0.3.193";
        String dz1  = "167969729";
        String zore = "000000000000000000000000000000000";
        String s = Integer.toBinaryString(167969729);
        System.out.println(s);
        String substring = zore.substring(0, 32 - s.length());
        String s1 = substring + s;
        System.out.println(s1.substring(0,8));
        System.out.println(s1.substring(8,16));
        System.out.println(s1.substring(16,24));
        System.out.println(s1.substring(24,32));

        int i1 = Integer.parseInt(s1.substring(0, 8), 2);
        int i2 = Integer.parseInt(s1.substring(8, 16), 2);
        int i3 = Integer.parseInt(s1.substring(16, 24), 2);
        int i4 = Integer.parseInt(s1.substring(24,32), 2);
        System.out.println(i1+"."+i2+"."+i3+"."+i4);

        System.out.println("----------------------");
        String[] split = dz.split("\\.");
        String s2 = Integer.toBinaryString(Integer.parseInt(split[0]));
        String bra = "";
        if(s2.length() != 8){
            bra += zore.substring(0, 8 - s2.length()) + s2;
        }else{
            bra += s2;
        }
        String s3 = Integer.toBinaryString(Integer.parseInt(split[1]));
        if(s3.length() != 8){
            bra +=  zore.substring(0, 8 - s3.length()) + s3;
        }else{
            bra += s3;
        }
        String s4 = Integer.toBinaryString(Integer.parseInt(split[2]));
        if(s4.length() != 8){
            bra += zore.substring(0, 8 - s4.length()) + s4;
        }else{
            bra += s4;
        }
        String s5 = Integer.toBinaryString(Integer.parseInt(split[3]));
        if(s5.length() != 8){
            bra += zore.substring(0, 8 - s5.length()) + s5;
        }else{
            bra += s5;
        }
        System.out.println(bra);
        int i = Integer.parseInt(bra, 2);
        System.out.println(i);
    }

    @Test
    public void sdsdszd(){
        System.out.println(f(3));
    }
    public static int f(int i){
        if(i==1 || i==2){
            return 1;
        }
        return f(i-1)+f(i+2);
    }
    @Test
    public void dsdaggg(){
        BigDecimal one = new BigDecimal("1");
        BigDecimal devideYinzi = new BigDecimal("2");
        BigDecimal distance = new BigDecimal("0");
        for (int j = 0; j < 5; j++) {
            BigDecimal multiply = one.divide(devideYinzi.pow(j)).multiply(new BigDecimal("1.5"));
            distance = distance.add(multiply);
        }
        System.out.println(distance.toPlainString());
    }

    @Test
    public void sdsfgkjlj(){
            String yanma = "255.255.252.0";
            String ip1 = "173.225.245.45";
            String ip2 = "69.138.93.228";
            String[] yanmas = yanma.split("\\.");
            String[] ip1s = ip1.split("\\.");
            String[] ip2s = ip2.split("\\.");
        if(Integer.parseInt(yanmas[0]) != 255 || Integer.parseInt(yanmas[3])!=0){
            System.out.println("1");
            return;
        }
            for (int i = 0; i < 4; i++) {
                int i1 = Integer.parseInt(yanmas[i]);
                int i2 = Integer.parseInt(ip1s[i]);
                int i3 = Integer.parseInt(ip2s[i]);
                if(i1<0 || i1>255){
                    System.out.println("1");
                    return;
                }
                if(i2<0 || i2>255){
                    System.out.println("1");
                    return;
                }
                if(i3<0 || i3>255){
                    System.out.println("1");
                    return;
                }
            }
        for (int i = 0; i < 4; i++) {
            int i1 = Integer.parseInt(yanmas[i]);
            int i2 = Integer.parseInt(ip1s[i]);
            int i3 = Integer.parseInt(ip2s[i]);
            int s1= i1&i2;
            int s2= i1&i3;
            if(  s1 != s2){
                System.out.println("2");
                return;
            }
        }
        System.out.println("0");
    }
    @Test
    public void sdfsdfsdf(){
        System.out.println(255&193);
        System.out.println(255&232);
    }

    @Test
    public void fsdfsdfsdf(){
        String str = "1qazxsw23 edcvfr45tgbn hy67uj m,ki89ol.\\\\/;p0-=\\\\][";
        Pattern p1 = Pattern.compile("[a-zA-Z]");
        String p2 = " ";
        Pattern p3 = Pattern.compile("[0-9]");
        Pattern p4 = Pattern.compile("[^a-zA-Z0-9]");
        Pattern p5 = Pattern.compile("[\\W]");
        boolean x = Pattern.matches("[\\w]", "x");
        System.out.println(x);
    }

    @Test
    public void dsdsdsdsd() throws ScriptException {
        String s = "3+2*(1+2*(-4/(8-6)+7))";
        ScriptEngine nasharn = new ScriptEngineManager().getEngineByName("nashorn");
        Object eval = nasharn.eval(s);
        System.out.println(eval);


        ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");

    }

    @Test
    public void lsdfjsdf(){
        String dd = "zuixhuhyjgksyhqkjqxwylkoubykjxtcvkyqjpzgltbemmbmqibxxqpkgbvwbmjotixanvciibubglizmumcrjavakiygyuv";
        System.out.println(dd.length());

    }


    @Test
    public void dddd(){
        List<Integer> l = new ArrayList<>();
        l.add(1);l.add(3);l.add(5);l.add(7);l.add(2);
        l.sort(Integer::compareTo);
        System.out.println(l);
        System.out.println(count(5,5));
    }

    public int count(int m,int n)
    {
        if(m<0||n<=0)
            return 0;
        //细分到苹果数为一或盘子数为一的情况返回一
        if(m==1||n==1||m==0)
            return 1;
        //将此事件无线细分
        return count(m,n-1)+count(m-n,n);
    }

    @Test
    public void dsdsd(){
        String ddd = "(AB)";
        Map<String,List<Integer>> map = new HashMap<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(36);
        l1.add(4);

        List<Integer> l2 = new ArrayList<>();
        l2.add(4);
        l2.add(17);

//        List<Integer> l3 = new ArrayList<>();
//        l3.add(20);
//        l3.add(5);
        List<List<Integer>> lll = new ArrayList<>();
        lll.add(l1);lll.add(l2);
//        lll.add(l3);

        List<String> strings = Arrays.asList(ddd.split(""));
        Stack<String> stack = new Stack<>();
        int s = 0;
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        int times = 0;
        for (String string : strings) {
            if (Pattern.matches("[A-Za-z]",string)) {
                map.put(string,lll.get(s));
                s++;
                stack.push(string);
            }else if(")".equals(string)){
                if(CollectionUtils.isEmpty(first)){
                    first = map.get(stack.pop());
                    second = map.get(stack.pop());
                    times = times + second.get(0) * first.get(1) * second.get(1);
                    List<Integer> first1 = new ArrayList<>();
                    first1.add(second.get(0));
                    first1.add(first.get(1));
                    first = first1;
                }else{
                    second = map.get(stack.pop());
                    times = times + second.get(0) * first.get(1) * second.get(1);
                    List<Integer> first1 = new ArrayList<>();
                    first1.add(second.get(0));
                    first1.add(first.get(1));
                    first = first1;
                }
            }
        }
        System.out.println(times);
    }

    @Test
    public void dsdzzsd(){
        String value = "U*w";
        value = value.toLowerCase(Locale.ROOT);
        String regx = value.replaceAll("\\*{2,}","\\*");
        regx = regx.replaceAll("\\?","[0-9a-z]{1}");
        regx = regx.replaceAll("\\*","[0-9a-z]{0,}");
        System.out.println(regx);

        System.out.println(Pattern.matches(regx,"uyw"));
    }

    @Test
    public void asdasdasdasd(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 34; j++) {
                int k=100-i*5-j*3;
                if(5*i+3*j+k*3==100){
                    System.out.println(i+" "+j+" "+k);
                }


            }
        }
    }
    @Test
    public void sss() throws ParseException {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        cd.setTime(sdf.parse("2012 12 31".replace(" ","")));
        cd.get(Calendar.DAY_OF_YEAR);
    }

    @Test
    public void dsdsgggg(){
        String ss = "gqpj /lrl d:\\ a:\\ c:\\ /nkb";
        List<String> l = Arrays.asList(ss.split(" "));
        System.out.println(l.size());
        for(String str : l){
            System.out.println(str);
        }

    }
    static List<String> l = new ArrayList<String>(); //储存结果
    @Test
    public void dsdsdsdsds(){
        int[] id = {1, 2, 3};
        Stack<Integer> stack = new Stack<Integer>();
        trainOut(id, 0, stack, "", 0);
        //对结果集排序
        Collections.sort(l);
        for (String str : l) {
            System.out.println(str);
        }
    }

    public void trainOut(int[] id, int i, Stack<Integer> s, String str, int n) {
        if (n == id.length) {
            l.add(str); //如果所有火车均出栈则将当前结果保存
        }

        if (!s.empty()) { //栈非空时出栈
            int temp = s.pop();
            trainOut(id, i, s, str + temp + " ", n + 1);
            s.push(temp); //恢复现场
        }

        if (i < id.length) {
            s.push(id[i]);
            trainOut(id, i + 1, s, str, n);
            s.pop(); //恢复现场

        }
    }
    @Data
    class Dd{
        private Integer in;
        private Integer out;
        List<Integer> integers;
        List<String> ll;
        String stackStr;
    }
    @Test
    public void dsdsdsdsdzs(){
        Dd dd = new Dd();
        Integer[] ids = {1,2,3,4};
        List<Integer> integers = Arrays.asList(ids);
        List<String> ll = new ArrayList<>();
        dd.setIn(4);
        dd.setOut(4);
        dd.setIntegers(integers);
        dd.setLl(ll);

        op(dd);

        System.out.println(dd.getLl());
    }


    void op(Dd dd){
        in(dd);
        out(dd);
    }
    void in(Dd dd){
        int in = dd.getIn();
        int out = dd.getOut();
        List<String> ll = dd.getLl();

        String stackStr = dd.getStackStr();
        stackStr = stackStr+in+"进 ";
        dd.setStackStr(stackStr);
        dd.setIn(in-1);
        if(in == 0 && out == 0){
            ll.add(stackStr);
            dd.setStackStr("");
            return;
        }
        op(dd);
    }

    void out(Dd dd){
        int in = dd.getIn();
        int out = dd.getOut();
        List<String> ll = dd.getLl();

        String stackStr = dd.getStackStr();
        stackStr = stackStr+in+"进 ";
        dd.setStackStr(stackStr);
        dd.setOut(out-1);
        if(in == 0 && out == 0){
            ll.add(stackStr);
            dd.setStackStr("");
            return;
        }
        op(dd);
    }

    @Test
    public void dsdsdsd(){
        ScriptEngine se = new ScriptEngineManager().getEngineByName("nashorn");
        String[] nums = "str".split("\\.", -1);
        String[] numss = "str".split("\\.");

        int i = Integer.parseInt("05");
        System.out.println(i);
    }

    @Test
    public void 走格子(){
        int i = 2;
        int j = 2;
        int cal = cal(i, j);
        System.out.println(cal);
    }

    int cal(int i,int j){
        if(i == 1 || j==1){
            return i+j;
        }
        return cal(i-1,j)+cal(i,j-1);
    }

    @Test
    public void sdsds(){
        String s = "abcd12345ed125ss123058789".replaceAll("[a-z]", ",");
        System.out.println(s);
        String[] split = s.split(",");
        System.out.println(split[0]);
    }

    //数组分组的递归函数，l是非 3 5加成的集合   targrt是集合的总和/2-sum3（sum3是3的集合总和），startindex是起始位置，它从0开始
    //又是动态规划
    boolean helper(List<Integer> l,int target,int startIndex){
        if(startIndex == l.size()){
           return target == 0;
        }
        return helper(l,target-l.get(startIndex),startIndex+1)
                || helper(l,target,startIndex+1);
    }

    @Test
    public void RMB(){
        String rmb = ".12";
        String[] split = rmb.split("\\.");
        System.out.println(Arrays.asList(split));
    }

    @Test
    public void dsdsdzd(){
        String s = "Jkdi234klowe90a3".replaceAll("([0-9]+)", "*$1*");
        System.out.println(s);
        boolean matches = Pattern.matches("[123789]", "5");
        System.out.println(matches);
        Pattern p = Pattern.compile("[a-z]");

        boolean matchess = Pattern.matches("[a]+[b]", "aaaab");
        System.out.println(matchess);

        boolean matchesss = Pattern.matches("[\\^a]", "aaaab");
        System.out.println(matchesss);
    }
    @Test
    public void dsdsdfsdf(){
        int start = 2;
        int adder = 3;
        int result = 0;
        for (int i = 0; i < 5; i++) {
            result = result + (start+adder*i);
        }
        System.out.println(result);
    }

    @Test
    public void dsdsddf(){
        Object[] obs = {"1","b","3","5","z","g"};
        Arrays.sort(obs);
        for (int i = 0; i < obs.length; i++) {
            System.out.println(obs[i]);
        }

    }
    @Data
    @AllArgsConstructor
    class Rangae implements Comparable<Rangae>{
        int start;
        int end;

        @Override
        public int compareTo(Rangae o) {
            if(this.start > o.getStart()){
                return 1;
            }else if(o.getStart() > this.start){
                return -1;
            }else{
                if(this.end > o.getEnd()){
                    return 1;
                }else if(o.getEnd() > this.end){
                    return -1;
                }
                return 0;
            }
        }
    }

    @Test
    public void sdsdsdsd(){
        List<Rangae> rs = new ArrayList<>();
        rs.add(new Rangae(50,80));
        rs.add(new Rangae(50,60));
        rs.add(new Rangae(40,90));
        Collections.sort(rs);
        System.out.println(rs);
    }

    @Test
    public void dsdsdsdjj(){
        Stack s = new Stack();
        int size = s.size();
        String deptStr = "()(()())";
        char[] chars = deptStr.toCharArray();
        Integer deepMove = 0;
        Integer deep = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '('){
                s.push(chars[i]);
                deepMove = s.size();
                if(deepMove > deep){
                    deep = deepMove;
                }
            }
            if(chars[i] == ')'){
                s.pop();
            }
        }
        System.out.println(deep);
    }

    class treeNode{
        Integer val;
        Integer left;
        Integer right;
    }

    @Test
    public void dsdsdsdsdfff(){
        Integer[] as = {1,3,5};
        Integer[] bs = {2,4,6};
        List<Integer> integers1 = Arrays.asList(as);
        List<Integer> integers2 = Arrays.asList(bs);
        remove(integers1,integers2);
    }

    void remove(List<Integer> integers1,List<Integer> integers2){
        if(integers2.size() == 0 || integers1.size()==0){
            return;
        }
        List<Integer> result1 = new ArrayList<>(integers1);
        List<Integer> result2 = new ArrayList<>(integers2);
        for (int i = 0; i < result1.size(); i++) {
            for (int i1 = 0; i1 < result2.size(); i1++) {
                try{
                    System.out.println(result1.get(i)+","+result2.get(i1));
                    result2.remove(i1);
                    remove(result1,result2);
                }catch(Exception e){}
            }
        }
    }




















    @Test
    public void sdsdsdsdsffgfg(){
        String str = "The furthest distance in the world, Is not between life and death, " +
                "But when I stand in front of you, Yet you don't know that I love you.";
        String pre = "f";
        str = str.replaceAll("[^a-zA-Z]"," ");
        String[] strs = str.split(" ");
        Set<String> s = new HashSet();
        for(int i=0;i<strs.length;i++){
            if(strs[i].startsWith(pre)){
                s.add(strs[i]);
            }
        }
        if(s.size() == 0){
            System.out.print(pre);
        }else{
            String[] strResult = new String[s.size()];
            Iterator<String> iterator = s.iterator();
            for (int i = 0; i < strResult.length; i++) {
                strResult[i] = iterator.next();
            }
            Arrays.sort(strResult);
            for (int i = 0; i < strResult.length; i++) {
                System.out.print(strResult[i]+" ");
            }

        }
    }

    @Test
    public void ssdfnxbcvnxcv(){
        String dd = "AAX";
        System.out.println(dd.startsWith("aa"));
    }


    @Test
    public void kasdmmn(){
        Integer[] is = {1,2,3,4,5,6,7,8,9,10};
        Integer first = is[0];
        double d = 0;
        int total = 0;
        for (int i = 0; i < is.length; i++) {
            total = total + is[i];
        }
        d = total/2;
        double distance = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        System.out.println(d);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                i1 = is[j];
                is[j] = 0;
                for (int k = 0; k < 10; k++) {
                    i2 = is[k];
                    is[k] = 0;
                    for (int m = 0; m < 10; m++) {
                        i3 = is[m];
                        is[m] = 0;
                        for (int n = 0; n < 10; n++) {
                            i4 = is[n];
                            is[n] = 0;
                            for (int o = 0; o < 10; o++) {
                                i5 = is[o];
                                is[o] = 0;
                                if(i1 == first && i2 == 0&& i3 == 0&& i4 == 0&& i5 == 0){
                                    distance = d-(i1+i2+i3+i4+i5);
                                }
                                if(d > i1+i2+i3+i4+i5 && d-(i1+i2+i3+i4+i5) < distance){
                                    distance = d-(i1+i2+i3+i4+i5);
                                    System.out.println(i1+""+i2+i3+i4+i5);
                                    i1 = 0;i2 = 0;i3 = 0;i4 = 0;i5 = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void dsddsffdfd(){
        Integer[] is = {1,2,3,4,5,6,7,8,9,10};
        double total = 0;
        for (int i = 0; i < is.length; i++) {
            total+=is[i];
        }
        double d = total/2;
        System.out.println(d);
        int maybe1 = is[1] + is[3] + is[5] + is[7] + is[9];
        int maybe2 = is[0] + is[9] + is[3] + is[7] + is[4];
        int maybe3 = is[3] + is[4] + is[5] + is[6] + is[7];
        int maybe4 = is[2] + is[3] + is[4] + is[5] + is[6];
        int maybe5 = is[1] + is[2] + is[3] + is[7] + is[8];
        int maybe6 = is[0] + is[1] + is[2] + is[8] + is[9];
        int maybe7 = is[0] + is[1] + is[3] + is[8] + is[9];
        int maybe8 = is[0] + is[1] + is[3] + is[7] + is[8];
        int maybe9 = is[1] + is[2] + is[6] + is[7] + is[8];
        List<Double> l = new ArrayList<Double>();
        if(d-maybe1 > 0){
            l.add(d-maybe1);
        }else{
            l.add(maybe1-d);
        }
        if(d-maybe2 > 0){
            l.add(d-maybe2);
        }else{
            l.add(maybe2-d);
        }
        if(d-maybe3 > 0){
            l.add(d-maybe3);
        }else{
            l.add(maybe3-d);
        }
        if(d-maybe4 > 0){
            l.add(d-maybe4);
        }else{
            l.add(maybe4-d);
        }
        if(d-maybe5 > 0){
            l.add(d-maybe5);
        }else{
            l.add(maybe5-d);
        }
        if(d-maybe6 > 0){
            l.add(d-maybe6);
        }else{
            l.add(maybe6-d);
        }

        if(d-maybe7 > 0){
            l.add(d-maybe7);
        }else{
            l.add(maybe7-d);
        }
        if(d-maybe8 > 0){
            l.add(d-maybe8);
        }else{
            l.add(maybe8-d);
        }
        if(d-maybe9 > 0){
            l.add(d-maybe9);
        }else{
            l.add(maybe9-d);
        }
        Collections.sort(l);
        System.out.println(l.get(0)*2);


    }

    @Test
    public void sdsdsfsdfsdfsdf(){
        List<Node> l = new ArrayList<Node>();
        l.add(new Node(3,5,3,1));
        l.add(new Node(3,4,3,1));
        l.add(new Node(2,8,3,1));
        Collections.sort(l);
        System.out.println(l);
        Integer totalPrice = 0;
        for (int i = 0; i < l.size(); i++) {
            Node node = l.get(i);
            if(node.builded == 0){
                totalPrice +=node.getPrice();
            }
        }
        System.out.println(totalPrice);
        for (int i = 0; i < l.size(); i++) {
            Node node = l.get(i);
        }
    }
    class Node implements Comparable<Node>{
        Integer currentNode;
        Integer nextNode;
        Integer price;
        Integer builded;



        @Override
        public int hashCode() {
            return Objects.hash(currentNode, nextNode, price, builded);
        }

        public Integer getCurrentNode() {
            return currentNode;
        }

        public void setCurrentNode(Integer currentNode) {
            this.currentNode = currentNode;
        }

        public Integer getNextNode() {
            return nextNode;
        }

        public void setNextNode(Integer nextNode) {
            this.nextNode = nextNode;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getBuilded() {
            return builded;
        }

        public void setBuilded(Integer builded) {
            this.builded = builded;
        }

        public Node(Integer currentNode, Integer nextNode, Integer price, Integer builded) {
            this.currentNode = currentNode;
            this.nextNode = nextNode;
            this.price = price;
            this.builded = builded;
        }

        @Override
        public int compareTo(Node o) {
            if(this.currentNode > o.getCurrentNode()){
                return 1;
            }else if(this.currentNode < o.getCurrentNode()){
                return -1;
            }else{
                if(this.nextNode > o.getNextNode()){
                    return 1;
                }else if(this.nextNode < o.getNextNode()){
                    return -1;
                }
                return 0;
            }

        }
    }

    @Test
    public void dsfkskfjhkdfg(){
        Integer[] is = {1,2,33,44,56,6,8,9,10,7};
        Arrays.sort(is);
        List<Nodes> l = new ArrayList<>();
        double d = 87.5;
        for (int i = 0; i < is.length; i++) {
            for (int j = 0; j < is.length; j++) {
                for (int k = 0; k < is.length; k++) {
                    for (int m = 0; m < is.length; m++) {
                        for (int n = 0; n < is.length; n++) {
                            l.add(new Nodes(i,j,k,m,n));
                        }
                    }
                }
            }
        }
        System.out.println(l.size());
        Iterator<Nodes> iterator = l.iterator();
        while(iterator.hasNext()){
            Nodes next = iterator.next();
            if(next.shouldBeRemoved()){
                iterator.remove();
            }
        }
        System.out.println(l.size());
        System.out.println(l);
        System.out.println("       ");
        Iterator<Nodes> iterator1 = l.iterator();
        double dist = 87.5;
        while(iterator1.hasNext()){
            Nodes next = iterator1.next();
            double totalScore = is[next.getI1()]+is[next.getI2()]+is[next.getI3()]+is[next.getI4()]+is[next.getI5()];
            if(Math.abs(d-totalScore) < dist){
                dist = Math.abs(d-totalScore);
                System.out.println(">>>>>>"+next.toString());
            }
        }
        System.out.println(dist*2);
    }
    @Data
    @AllArgsConstructor
    class Nodes{
        int i1;
        int i2;
        int i3;
        int i4;
        int i5;

        @Override
        public String toString() {
            return "Nodes{" +
                    "i1=" + i1 +
                    ", i2=" + i2 +
                    ", i3=" + i3 +
                    ", i4=" + i4 +
                    ", i5=" + i5 +
                    '}';
        }

        public boolean shouldBeRemoved(){
            String str = i1+""+i2+i3+i4+i5;
            if(str.length() - str.replace(i1+"","").length() > 1){
                return true;
            }
            if(str.length() - str.replace(i2+"","").length() > 1){
                return true;
            }
            if(str.length() - str.replace(i3+"","").length() > 1){
                return true;
            }
            if(str.length() - str.replace(i4+"","").length() > 1){
                return true;
            }
            if(str.length() - str.replace(i5+"","").length() > 1){
                return true;
            }
            return false;
        }

    }

}
