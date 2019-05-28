CREATE PROCEDURE add_dates()

BEGIN
DECLARE a INT Default 1 ;
simple_loop: LOOP

UPDATE tree_node SET date =

    FROM_UNIXTIME(
         RAND() *
            (UNIX_TIMESTAMP('2009-01-01 00:00:00') - UNIX_TIMESTAMP('2019-03-25 00:00:00')) +
             UNIX_TIMESTAMP('2019-03-25 00:00:00')
                  ) where id = a;

SET a=a+1;
IF a=523001 THEN
LEAVE simple_loop;
END IF;
END LOOP simple_loop;
END