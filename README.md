# Description

RDServiceRestorer is a CloudNet module allows to restore or wipe services by time without outside interference.

# Compatibility

- Created for CloudNet v4.0.0 RC9 and newer
- Requires Java 17 or later

# Module setup
- Drop the file into your modules folder
- Create module directory "RDServiceRestorer"
- Create directory "restorers" in module directory
- Put restorer configs in json format into this folder

# Configuration
- "id" - unique id for the restorer
- "enabled" - should restorer works
- "removeFiles" - should service be created from template (Works only with static services)
- "tasks" - list of tasks, which could be restored
- "delay" - delay between restores by time

## Example:
```
{
	"id": "unique_id",
	"enabled": true,
	"removeFiles": true,
	"tasks": [ "task1", "task2" ],
	"delay": 60000
}

```

# TODO List

- [ ] Alerts about an upcoming reboot
- [x] Reboots without deleting files
- [x] Restoring specific services ('restorer restore <restorer_id>')
- [x] Restoring all services ('restorer restoreAll')
- [x] Reload command ('restorer reload') 
- [x] Restoring non-static servers
