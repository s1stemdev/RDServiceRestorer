# Description

RDServiceRestorer is a CloudNet module allows to restore or wipe services by time without outside interference.

# Compatibility

- Created for CloudNet v4.0.0 RC9 and newer
- Requires Java 17 or later

# Configuration

```
{
	"id": "unique id",
	"enabled": true,
	"tasks": [ "task1", "task2" ],
	"delay": time in milliseconds (ex. 60000 - 1 minute)
}

```

# TODO List

- [ ] Alerts about an upcoming reboot
- [ ] Reboots without deleting files
- [x] Restoring non-static servers
