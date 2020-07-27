# auth-server

### 1.证书

###### 生成证书
````
keytool -genkeypair -alias eboot-jwt -validity 3650 -keyalg RSA -dname "CN=jwt_1,OU=jtw_2,O=jtw_3,L=jwt_4,S=jwt_5,C=jwt_6" -keypass jwt-eboot -keystore eboot-jwt.jks -storepass eboot-secret
````

###### JDK中keytool常用命令:
````
-genkey      在用户主目录中创建一个默认文件".keystore",还会产生一个mykey的别名，mykey中包含用户的公钥、私钥和证书(在没有指定生成位置的情况下,keystore会存在用户系统默认目录)
-alias       产生别名,每个keystore都关联这一个独一无二的alias，这个alias通常不区分大小写
-keystore    指定密钥库的名称(产生的各类信息将不在.keystore文件中)
-keyalg      指定密钥的算法 (如 RSA  DSA（如果不指定默认采用DSA）)
-validity    指定创建的证书有效期多少天
-keysize     指定密钥长度
-storepass   指定密钥库的密码(获取keystore信息所需的密码)
-keypass     指定别名条目的密码(私钥的密码)
-dname       指定证书拥有者信息 
-list        显示密钥库中的证书信息     
-v           显示密钥库中的证书详细信息
-export      将别名指定的证书导出到文件 
-file        参数指定导出到文件的文件名
-delete      删除密钥库中某条目 
-printcert   查看导出的证书信息  
-keypasswd   修改密钥库中指定条目口令   
-import      将已签名数字证书导入密钥库 

#下面是各选项的缺省值。
-alias "mykey"
-keyalg "DSA"
-keysize 1024
-validity 90
-keystore 用户宿主目录中名为 .keystore 的文件
-file 读时为标准输入，写时为标准输出
````
 
###### 导出公钥
````
keytool -list -rfc --keystore eboot-jwt.jks -storepass eboot-secret | openssl x509 -inform pem -pubkey
````