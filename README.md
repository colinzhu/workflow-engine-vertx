# Event Driver Workflow Engine (Vert.x)

A design of workflow engine - Vert.x version

### Feature
When an event is received, the corresponding handled will be invoked to handle the event, after handling, a new event will be generated.

If the new event is not linked with any handler, the process will stop.

### Rules
The rules are defined in a list of Event Name and Handler mapping

### Example Rules
```
IF......................THEN....................................................................
eventName               handlerClassName                                           outputEvent
RECEIVED                colinzhu.workflow.example.handler.PaymentSaveHandler       SAVED
SAVED                   colinzhu.workflow.example.handler.PaymentCheckHandler      BLOCKED, RELEASED
RQST_RECEIVED_EXP_CLEAR	colinzhu.workflow.example.handler.PaymentClearExpHandler   BLOCKED, RELEASED
RQST_RECEIVED_STOP      colinzhu.workflow.example.handler.PaymentStopHandler       STOPPED
RQST_RECEIVED_CANCEL    colinzhu.workflow.example.handler.PaymentCancelHandler     CANCELLED
BLOCKED
STOPPED
CANCELLED
RELEASED

```
