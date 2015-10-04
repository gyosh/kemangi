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
ALAyISME iTu TETAP ada di Jakarta
Saya tidur... kemarin
Pin  BB saya   B12A3FC
bbm koq naik, warga sedih #edisicurhat
dia memblokir website http://www.lucu.com
éà ada karakter ga jelas, non ASCII appeared! #wow
Pak kepala desa tidak tahu bahwa 3 pencuri di rumah itu adalah teman lamanya!
```

After case folding, non-alphanumeric removal, stop words removal, and stemming:

```
main peran 12 domba pentas
alayisme tetap jakarta
tidur kemarin
pin bb b12a3fc
bbm naik warga sedih
blokir website
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

## Downloads

* [kemangi-v1.0.0](https://github.com/gyosh/kemangi/releases/download/1.0.0/kemangi-1.0.0.jar)

## Installation

1. Download [Kemangi](#downloads)
2. Download and install [Java Runtime Environment](http://www.oracle.com/technetwork/java/javase/downloads/jre7-downloads-1880261.html)
3. Run Kemangi:
  * For Windows user, right click on downloaded Kemangi application, and select open with Java. 
  * For Linux user:
```
java -jar path/to/kemangi-VERSION.jar
```

## How to Use

Check out [Kemangi's documentation](http://kemangi.readthedocs.org/en/latest/index.html).

## Term And Condition
By using Kemangi, you need to agree for this [term and condition](http://fws.cs.ui.ac.id/StemmerSampleClient/TermAndCondition.jsp).

## License
Kemangi is licensed under MIT License.
