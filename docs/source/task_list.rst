List of Supported Task
======================

Each task is like a filter pipe. It receives input from one end, filter it, and outputs to the other end which is possibly another pipe's entrance.
With this pattern, several pipes can be arranged successively, creating a workflow that you can customize.

Here are kind of task that Kemangi can do:

..  toctree::
    :maxdepth: 1

    task/case_folding
    task/non_alpha_numeric_removal
    task/own_stop_word_removal
    task/stop_word_removal
    task/stem
