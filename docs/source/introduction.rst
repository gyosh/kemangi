Introduction
============

Overview
--------

Kemangi is Indonesian Language (Bahasa Indonesia) pre-processor tool.

If you are going to conduct text mining related research with Indonesian Language, there is a high probability that pre-processing is needed.
The purpose of Kemangi is to clean up your text file so that it is easier to be processed.

Kemangi provides basic task pre-processing task:

* Remove non-ASCII characters
* Remove non-alphanumeric characters
* Case folding
* Remove stop words (meaningless word)
* Remove words according to pattern provided by you, e.g. hashtags, URLs, Twitter mentions, etc
* Word stemming

Example
-------

Raw input::

  Mempermainkan peranan 12 domba di pementasan
  ALAyISME iTu TETAP ada di Jakarta
  Saya tidur... kemarin
  Pin  BB saya   B12A3FC
  bbm koq naik, warga sedih #edisicurhat
  dia memblokir website http://www.lucu.com
  éà ada karakter ga jelas, non ASCII appeared! #wow
  Pak kepala desa tidak tahu bahwa 3 pencuri di rumah itu adalah teman lamanya!

After case folding, non-alphanumeric removal, stop words removal, and stemming::

  main peran 12 domba pentas
  alayisme tetap jakarta
  tidur kemarin
  pin bb b12a3fc
  bbm naik warga sedih
  blokir website
  karakter ga jelas non ascii appeared
  pak kepala desa tahu 3 curi rumah teman

Issues and Discussion
---------------------
When you encounter issues in using Kemangi, please report it to `Kemangi's repository <https://github.com/gyosh/kemangi/issues>`_.
You need GitHub account to do that. Don't worry if you don't have it, just create one. GitHub is a community full of generous programmers.

Support Kemangi
---------------
Visit http://bit.ly/kemangi-feedback for feedback and comments. Kemangi developers would love to hear your story.
If you like Kemangi, don't forget to star it at upper right `Kemangi's repository <https://github.com/gyosh/kemangi/issues>`_ (need GitHub login).
