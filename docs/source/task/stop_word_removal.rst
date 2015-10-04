Stop Words Removal
==================

Description
-----------

Removes meaningless word for further processing like ``di``, ``saya``, or ``dari``.
Uses web service provided by Faculty of Computer Science, University of Indonesia.

Try it: http://fws.cs.ui.ac.id/StopwordRemoverSampleClient/index.jsp

This task includes case folding and remove non-alphanumeric characters.

Be warned, the word ``tidak`` (en: not) is also removed. Depending on what you are going to do next, removing this word may affect the result

Requirement
-----------

* Internet connection.

Example
-------

Sample input::

  Pak kepala desa tidak tahu bahwa 3 pencuri
  di rumah itu adalah teman lamanya!

Sample output::

  pak kepala desa tahu 3 pencuri
  rumah teman
