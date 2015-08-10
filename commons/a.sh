#!/bin/sh

# if file existed,need to be deleted
if [-e car_type.html];then
        $(rm car_type.html -f)
fi
# if file existed,need to be deleted
if [-e result.txt];then
        $(rm -f result.txt)
fi
for i in A B C D F G H J K L M N O P Q R S T W X Y Z ;do
        url="http://www.autohome.com.cn/grade/carhtml/$i.html"
        echo "url:"$url
        curl $url >> car_type.html
          
done      
          
for i in $(grep "<li id=\"s[0-9]*\">" car_type.html); do
        #statements 
        echo ${i//[^0-9]/} >> result.txt
done
