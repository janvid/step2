janrain step2 mirror
================================================================================

Initial Setup
--------------------------------------------------------------------------------

- `git clone ssh://git@github.com/janvid/step2`
- `cd step2`
- `git config -e`

```
[core]
    repositoryformatversion = 0
    filemode = true
    bare = false
    logallrefupdates = true
    ignorecase = true
    precomposeunicode = false
[svn-remote "svn"]
    url = http://step2.googlecode.com/svn/code/java/trunk
    fetch = :refs/remotes/git-svn
[remote "origin"]
    url = ssh://git@github.com/janvid/step2
    fetch = +refs/heads/*:refs/remotes/origin/*
[branch "master"]
    remote = origin
    merge = refs/heads/master
[branch "upstream"]
    remote = origin
    merge = refs/heads/upstream
```

Sync Mirror
--------------------------------------------------------------------------------

- `git checkout upstream`
- `git svn rebase`
- `git push origin HEAD`
- `git checkout master`
- `git merge --rebase upstream`

Sync Mirror
--------------------------------------------------------------------------------

Never make any changes in the `upstream` branch. That branch should always be a
direct mirror of the upstream svn repo.