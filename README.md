# Kemangi
### Easy Text Preprocessor For Indonesian Language
Also known as Bahasa Indonesia.

## Overview

Java based application to pre-process your Indonesian Language texts, for further intelligent stuff you are going to do such as text mining.
Several features consume web service provided by [University of Indonesia's Information Retrieval Lab](http://bahasa.cs.ui.ac.id/webservices.php).

## Example

Raw text:

```
Mempermainkan peranan 12 domba di pementasan
ALAyISME iTu UmUUM
Saya tidur... jam 12:30 kemarin
Pin  BB saya   B12A3FC
bbm koq naik, warga sedih #edisicurhat
website http://www.lucu.com diblokir menko
éà ada karakter ga jelas, non ASCII appeared! #wow
Pak kepala desa tidak tahu bahwa 3 pencuri di rumah itu adalah teman lamanya!
```

Processed text:
```
main peran 12 domba pentas
alayisme umuum
tidur jam 12 30 kemarin
pin bb b12a3fc
bbm naik warga sedih
website blokir menko
karakter ga jelas non ascii appeared
pak kepala desa tahu 3 curi rumah teman
```

## Features

### Remove non-ASCII characters
No more foreign character, weird symbols, or non-standard emoticon in your text.

### Case folding
Some people types "LiKe THiS", let's transform them into lowercase.

### Remove stop words defined by you
You probably don't need hashtags, mentions, URLs, or any specific word in your document.
Define those unwanted text/regex, Kemangi will do the rest.

### Remove default stop words
Colleagues in University of Indonesia have worked hard to build a [web service](http://fws.cs.ui.ac.id/StopwordRemover/StopwordRemover?wsdl) to do this.
Kemangi consumes the web service, and you can easily use that webservice.

### Stemming
Right from the inventor of [Indonesian Language's stemming algorithm](http://dl.acm.org/citation.cfm?id=1316459), [web service](http://fws.cs.ui.ac.id/StopwordRemover/StopwordRemover?wsdl) is also provided.

## Development
Started at 30/4/14 as command line tool. 
Developed as a request from Industrial Engineering students who were conducting text mining researches.

Got another feature request by 8/4/15, from next batch's students.
Now Kemangi is maintained and evolving towards something like [Weka](http://www.cs.waikato.ac.nz/ml/weka).

Currently under development, no stable release is ready.

## Term And Condition
By using Kemangi, you need to agree for this [term and condition](http://fws.cs.ui.ac.id/StemmerSampleClient/TermAndCondition.jsp).

## License
Kemangi is licensed under MIT License.
