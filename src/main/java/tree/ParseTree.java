package tree;

public class ParseTree {

    public static void main(String[] args) {
        String s1 = "boto3==1.17.1 (1.17.1)\n" +
                "├── botocore<1.21.0,>=1.20.1 (1.20.112)\n" +
                "│   ├── jmespath<1.0.0,>=0.7.1 (0.10.0)\n" +
                "│   ├── python-dateutil<3.0.0,>=2.1 (2.8.2)\n" +
                "│   │   └── six>=1.5 (1.16.0)\n" +
                "│   └── urllib3<1.27,>=1.25.4 (1.26.7)\n" +
                "├── jmespath<1.0.0,>=0.7.1 (0.10.0)\n" +
                "└── s3transfer<0.4.0,>=0.3.0 (0.3.7)\n" +
                "    └── botocore<2.0a.0,>=1.12.36 (1.20.112)\n" +
                "        ├── jmespath<1.0.0,>=0.7.1 (0.10.0)\n" +
                "        ├── python-dateutil<3.0.0,>=2.1 (2.8.2)\n" +
                "        │   └── six>=1.5 (1.16.0)\n" +
                "        └── urllib3<1.27,>=1.25.4 (1.26.7)";

        String[] spl = s1.split("\n");
        int index = 0;
        for(String eachD : spl) {
           // System.out.println(eachD);
            if(index > 0) {
                if((eachD.startsWith("├── ") || eachD.startsWith("└── "))&& !eachD.startsWith(" ")) {
                    System.out.println(eachD);
                }
            }
            index++;
        }
    }
}
