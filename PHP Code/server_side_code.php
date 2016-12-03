<?php

	if ($_SERVER['REQUEST_METHOD'] === 'POST') 
	{
	    $data = file_get_contents('php://input');
	    $json_data = json_decode($data , true);
	  
	    //code to process data
	    if ($data == "" || empty($json_data['user_id']) || empty($json_data['password']))
	    {
	        $response = array('status' => false, 'message' => 'Invalid Values');    
	    }
	    else
	    {
	        if($json_data['user_id']=='hasan' && $json_data['password']==123)
	        	$response = array('status' => true, 'message' => 'Wow! You are a valid user!');
	        else
	        	$response = array('status' => false, 'message' => 'User ID or password is not valid');
	    }
	    
	    echo json_encode($response);
	}
	else if($_SERVER['REQUEST_METHOD'] === 'GET')
	{
	    if (empty($_GET['user_id']))
	    {
	        $response = array('status' => false, 'message' => 'Invalid Values');    
	    }
	    else
	    {
	    	if($_GET['user_id']=='hasan')
	        	$response = array('status' => true, 'message' => 'Just read that 4,153,237 people got married last year, not to cause any trouble but shouldn\'t that be an even number?');
	        else
	        	$response = array('status' => false, 'message' => 'User ID is not valid');
	    }
	    
	    echo json_encode($response);
	}

?>