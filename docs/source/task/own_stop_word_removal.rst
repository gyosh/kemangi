Own Stop Words Removal
======================

Description
-----------

You need to provide a list of stop words, then it will be removed from your document.

The list of stop words must be placed in a text file, each word in a line.
Stop word can also be regular expression (regex) pattern, but it must not contain space.

If you are not familiar with regex, find out more `here <http://www.regexr.com/>`_.

Typical used regex pattern:

=========  ==========================================  =================
Type       Example                                     Regex pattern
=========  ==========================================  =================
URL        http://www.doge.com, https://www.wow.com    ``https?://.*``
Hashtag    #wow, #curcol, #love                        ``#.*``
Mention    @will.gozali                                ``@.*``
Numbers    021, 56001, 2123123123                      ``[0-9]+``
=========  ==========================================  =================

Requirement
-----------

* A plain text file containing list of stop words or regex pattern to be removed from your document. One word per line.

Example
-------

Sample stop words content::

  rp
  koq
  nih
  http://.*
  #.*

Sample input::

  harga cabai Rp 15.000,00
  harga cabai rp 15.000,00
  bbm koq naik, warga sedih #edisicurhat
  telah blokir website http://www.lucu.com

Sample output::

  harga cabai Rp 15.000,00
  harga cabai 15.000,00
  bbm naik, warga sedih
  telah blokir website
