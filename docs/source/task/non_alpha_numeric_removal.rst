Non-Alphanumeric Removal
========================

Description
-----------

Removes all non-alphanumeric characters.

Recall that alphanumerics are letters and numbers.
This operation may split a token into multiple tokens, e.g. ``stop!minyak`` becomes ``stop`` and ``minyak``.

Example
-------

Sample input::

  Saya tidur... kemarin
  TOLONG DIBENAHI!!! kapan beresnya??!?

Sample output::

  saya tidur kemarin
  TOLONG DIBENAHI kapan beresnya
