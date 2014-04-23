<?php
$db    = new PDO('jdbc:hsqldb:hsql://localhost:9001/TravelTogether;dbname=TravelTogether;charset=utf8', 'SA', '');
$sql   = sprintf('SELECT * FROM USERS_EVENTS JOIN EVENTS ON USERS_EVENTS.EVENT_ID = EVENTS.EVENT_ID')

$out = array()
foreach($db->query($sql) as $row) {
    $out[] = array(
        'id' => $row->id,
        'title' => $row->name,
        'url' => Helper::url($row->id),
        'start' => strtotime($row->datetime) . '000'
    );
}

echo json_encode(array('success' => 1, 'result' => $out));
exit;