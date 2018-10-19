/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package bullet

import org.lwjgl.generator.*

const val BULLET_LIBRARY = "LibBullet.initialize();"

fun config() {
    packageInfo(Module.BULLET, "Contains bindings to the ${url("http://bulletphysics.org/", "Bullet")} library.")

	Generator.registerLibraryInit(Module.BULLET, "LibBullet", "bullet")
}

val b3PhysicsClientHandle = "b3PhysicsClientHandle".handle
val b3SharedMemoryStatusHandle = "b3SharedMemoryStatusHandle".handle
val b3SharedMemoryCommandHandle = "b3SharedMemoryCommandHandle".handle

val UserDataValueType = "enum UserDataValueType".enumType

val b3BodyInfo = struct(Module.BULLET, "B3BodyInfo", nativeName = "struct b3BodyInfo") {
	charASCII.array("m_baseName", "", size = 1024)
	charASCII.array("m_bodyName", "for {@code btRigidBody}, it does not have a base, but can still have a body name from {@code urdf}", size = 1024)
}

val b3JointInfo = struct(Module.BULLET, "B3JointInfo", nativeName = "struct b3JointInfo") {
	charASCII.array("m_linkName", "", size = 1024)
	charASCII.array("m_jointName", "", size = 1024)
    int.member("m_jointType", "")
	int.member("m_qIndex", "")
	int.member("m_uIndex", "")
	int.member("m_jointIndex", "")
	int.member("m_flags", "")
	double.member("m_jointDamping", "")
	double.member("m_jointFriction", "")
	double.member("m_jointLowerLimit", "")
	double.member("m_jointUpperLimit", "")
	double.member("m_jointMaxForce", "")
	double.member("m_jointMaxVelocity", "")
	double.array("m_parentFrame", "position and orientation (quaternion)", size = 7)
	double.array("m_childFrame", "position and orientation (quaternion)", size = 7)
	double.array("m_jointAxis", "joint axis in parent local frame", size = 3)
	int.member("m_parentIndex", "")
}

val b3UserDataValue = struct(Module.BULLET, "B3UserDataValue", nativeName = "struct b3UserDataValue") {
	int.member("m_type", "")
	AutoSize("m_data1")..int.member("m_length", "")
	char.p.member("m_data1", "")
}

val b3DynamicsInfo = struct(Module.BULLET, "B3DynamicsInfo", nativeName = "struct b3DynamicsInfo") {
	double.member("m_mass", "")
	double.array("m_localInertialDiagonal", "", size = 3)
	double.array("m_localInertialFrame", "", size = 7)
	double.member("m_lateralFrictionCoeff", "")

	double.member("m_rollingFrictionCoeff", "")
	double.member("m_spinningFrictionCoeff", "")
	double.member("m_restitution", "")
	double.member("m_contactStiffness", "")
	double.member("m_contactDamping", "")
	int.member("m_activationState", "")
	double.member("m_angularDamping", "")
	double.member("m_linearDamping", "")
	double.member("m_ccdSweptSphereRadius", "")
	double.member("m_contactProcessingThreshold", "")
	int.member("m_frictionAnchor", "")
}

val b3UserConstraintState = struct(Module.BULLET, "B3UserConstraintState", nativeName = "struct b3UserConstraintState") {
	double.array("m_appliedConstraintForces", "", size = 6)
	int.member("m_numDofs", "")
}

val b3UserConstraint = struct(Module.BULLET, "B3UserConstraint", nativeName = "struct b3UserConstraint") {
	int.member("m_parentBodyIndex", "")
	int.member("m_parentJointIndex", "")
	int.member("m_childBodyIndex", "")
	int.member("m_childJointIndex", "")
	double.array("m_parentFrame", "", size = 7)
	double.array("m_childFrame", "", size = 7)
	double.array("m_jointAxis", "", size = 3)
	int.member("m_jointType", "")
	double.member("m_maxAppliedForce", "")
	int.member("m_userConstraintUniqueId", "")
	double.member("m_gearRatio", "")
	int.member("m_gearAuxLink", "")
	double.member("m_relativePositionTarget", "")
	double.member("m_erp", "")
}

val b3DebugLines = struct(Module.BULLET, "B3DebugLines", nativeName = "struct b3DebugLines") {
	AutoSize(3, "m_linesFrom", "m_linesTo", "m_linesColor")..int.member("m_numDebugLines", "")
	float.const.p.member("m_linesFrom", "")   //float x,y,z times 'm_numDebugLines'.
	float.const.p.member("m_linesTo", "")     //float x,y,z times 'm_numDebugLines'.
	float.const.p.member("m_linesColor", "")  //float red,green,blue times 'm_numDebugLines'.
}

val b3OpenGLVisualizerCameraInfo = struct(Module.BULLET, "B3OpenGLVisualizerCameraInfo", nativeName = "struct b3OpenGLVisualizerCameraInfo") {
	int.member("m_width", "")
	int.member("m_height", "")
	float.array("m_viewMatrix", "", size = 16)
	float.array("m_projectionMatrix", "", size = 16)

	float.array("m_camUp", "", size = 3)
	float.array("m_camForward", "", size = 3)

	float.array("m_horizontal", "", size = 3)
	float.array("m_vertical", "", size = 3)

	float.member("m_yaw", "")
	float.member("m_pitch", "")
	float.member("m_dist", "")
	float.array("m_target", "", size = 3)
}

val b3CameraImageData = struct(Module.BULLET, "B3CameraImageData", nativeName = "struct b3CameraImageData") {
	int.member("m_pixelWidth", "")
	int.member("m_pixelHeight", "")
	unsigned_char.const.p.member("m_rgbColorData", "")  //3*m_pixelWidth*m_pixelHeight bytes
	float.const.p.member("m_depthValues", "")           //m_pixelWidth*m_pixelHeight floats
	int.const.p.member("m_segmentationMaskValues", "")  //m_pixelWidth*m_pixelHeight ints
}

val b3ContactPointData = struct(Module.BULLET, "B3ContactPointData", nativeName = "struct b3ContactPointData") {
	//todo: expose some contact flags, such as telling which fields below are valid
	int.member("m_contactFlags", "")
	int.member("m_bodyUniqueIdA", "")
	int.member("m_bodyUniqueIdB", "")
	int.member("m_linkIndexA", "")
	int.member("m_linkIndexB", "")
	double.array("m_positionOnAInWS", "", size = 3)       //contact point location on object A, in world space coordinates
	double.array("m_positionOnBInWS", "", size = 3)       //contact point location on object A, in world space coordinates
	double.array("m_contactNormalOnBInWS", "", size = 3)  //the separating contact normal, pointing from object B towards object A
	double.member("m_contactDistance", "")          //negative number is penetration, positive is distance.

	double.member("m_normalForce", "")

	double.member("m_linearFrictionForce1", "")
	double.member("m_linearFrictionForce2", "")
	double.array("m_linearFrictionDirection1", "", size = 3)
	double.array("m_linearFrictionDirection2", "", size = 3)
}
val b3ContactInformation = struct(Module.BULLET, "B3ContactInformation", nativeName = "struct b3ContactInformation") {
	AutoSize("m_contactPointData")..int.member("m_numContactPoints", "")
	b3ContactPointData.p.buffer("m_contactPointData", "")
}

val b3OverlappingObject = struct(Module.BULLET, "B3OverlappingObject", nativeName = "struct b3OverlappingObject") {
    int.member("m_objectUniqueId", "")
	int.member("m_linkIndex", "")
}
val b3AABBOverlapData = struct(Module.BULLET, "B3AABBOverlapData", nativeName = "struct b3AABBOverlapData") {
	AutoSize("m_overlappingObjects")..int.member("m_numOverlappingObjects", "")
	b3OverlappingObject.p.buffer("m_overlappingObjects", "")
}

private const val VISUAL_SHAPE_MAX_PATH_LEN = 1024
val b3VisualShapeData = struct(Module.BULLET, "B3VisualShapeData", nativeName = "struct b3VisualShapeData") {
    int.member("m_objectUniqueId", "")
	int.member("m_linkIndex", "")
	int.member("m_visualGeometryType", "")  //box primitive, sphere primitive, triangle mesh
	double.array("m_dimensions", "", size = 3)    //meaning depends on.member("m_visualGeometryType", "")
	charASCII.array("m_meshAssetFileName", "", size = VISUAL_SHAPE_MAX_PATH_LEN)
	double.array("m_localVisualFrame", "", size = 7)  //pos[3], orn[4]
								   //todo: add more data if necessary (material color etc, although material can be in asset file .obj file)
	double.array("m_rgbaColor", "", size = 4)
	int.member("m_tinyRendererTextureId", "")
	int.member("m_textureUniqueId", "")
	int.member("m_openglTextureId", "")
}
val b3VisualShapeInformation = struct(Module.BULLET, "B3VisualShapeInformation", nativeName = "struct b3VisualShapeInformation") {
	AutoSize("m_visualShapeData")..int.member("m_numVisualShapes", "")
	b3VisualShapeData.p.buffer("m_visualShapeData", "")
}

val b3CollisionShapeData = struct(Module.BULLET, "B3CollisionShapeData", nativeName = "struct b3CollisionShapeData") {
    int.member("m_objectUniqueId", "")
	int.member("m_linkIndex", "")
	int.member("m_collisionGeometryType", "")      //GEOM_BOX, GEOM_SPHERE etc
	double.array("m_dimensions", "", size = 3)           //meaning depends on m_visualGeometryType GEOM_BOX: extents, GEOM_SPHERE: radius, GEOM_CAPSULE+GEOM_CYLINDER:length, radius, GEOM_MESH: mesh scale
	double.array("m_localCollisionFrame", "", size = 7)  //pos[3], orn[4]
	charASCII.array("m_meshAssetFileName", "", size = VISUAL_SHAPE_MAX_PATH_LEN)
}
val b3CollisionShapeInformation = struct(Module.BULLET, "B3CollisionShapeInformation", nativeName = "struct b3CollisionShapeInformation") {
	AutoSize("m_collisionShapeData")..int.member("m_numCollisionShapes", "")
	b3CollisionShapeData.p.buffer("m_collisionShapeData", "")
}

val b3PhysicsSimulationParameters = struct(Module.BULLET, "B3PhysicsSimulationParameters", nativeName = "struct b3PhysicsSimulationParameters") {
	double.member("m_deltaTime", "")
	double.array("m_gravityAcceleration", "", size = 3)
	int.member("m_numSimulationSubSteps", "")
	int.member("m_numSolverIterations", "")
	int.member("m_useRealTimeSimulation", "")
	int.member("m_useSplitImpulse", "")
	double.member("m_splitImpulsePenetrationThreshold", "")
	double.member("m_contactBreakingThreshold", "")
	int.member("m_internalSimFlags", "")
	double.member("m_defaultContactERP", "")
	int.member("m_collisionFilterMode", "")
	int.member("m_enableFileCaching", "")
	double.member("m_restitutionVelocityThreshold", "")
	double.member("m_defaultNonContactERP", "")
	double.member("m_frictionERP", "")
	double.member("m_defaultGlobalCFM", "")
	double.member("m_frictionCFM", "")
	int.member("m_enableConeFriction", "")
	int.member("m_deterministicOverlappingPairs", "")
	double.member("m_allowedCcdPenetration", "")
	int.member("m_jointFeedbackMode", "")
	double.member("m_solverResidualThreshold", "")
	double.member("m_contactSlop", "")
	int.member("m_enableSAT", "")
	int.member("m_constraintSolverType", "")
	int.member("m_minimumSolverIslandSize", "")
}

val b3JointSensorState = struct(Module.BULLET, "B3JointSensorState", nativeName = "struct b3JointSensorState") {
	double.member("m_jointPosition", "")
	double.member("m_jointVelocity", "")
	double.array("m_jointForceTorque", "", size = 6) /* note to roboticists: this is NOT the motor torque/force, but the spatial reaction force vector at joint */
	double.member("m_jointMotorTorque", "")
}

///b3LinkState provides extra information such as the Cartesian world coordinates
///center of mass (COM) of the link, relative to the world reference frame.
///Orientation is a quaternion x,y,z,w
///Note: to compute the URDF link frame (which equals the joint frame at joint position 0)
///use URDF link frame = link COM frame * inertiaFrame.inverse()
val b3LinkState = struct(Module.BULLET, "B3LinkState", nativeName = "struct b3LinkState") {
	//m_worldPosition and m_worldOrientation of the Center Of Mass (COM)
	double.array("m_worldPosition", "", size = 3)
	double.array("m_worldOrientation", "", size = 4)

	double.array("m_localInertialPosition", "", size = 3)
	double.array("m_localInertialOrientation", "", size = 4)

	///world position and orientation of the (URDF) link frame
	double.array("m_worldLinkFramePosition", "", size = 3)
	double.array("m_worldLinkFrameOrientation", "", size = 4)

	double.array("m_worldLinearVelocity", "", size = 3)   //only valid when ACTUAL_STATE_COMPUTE_LINKVELOCITY is set (b3RequestActualStateCommandComputeLinkVelocity)
	double.array("m_worldAngularVelocity", "", size = 3)  //only valid when ACTUAL_STATE_COMPUTE_LINKVELOCITY is set (b3RequestActualStateCommandComputeLinkVelocity)

	double.array("m_worldAABBMin", "", size = 3)  //world space bounding minium and maximum box corners.
	double.array("m_worldAABBMax", "", size = 3)
}

val b3RayHitInfo = struct(Module.BULLET, "B3RayHitInfo", nativeName = "struct b3RayHitInfo") {
    double.member("m_hitFraction", "")
	int.member("m_hitObjectUniqueId", "")
	int.member("m_hitObjectLinkIndex", "")
	double.array("m_hitPositionWorld", "", size = 3)
	double.array("m_hitNormalWorld", "", size = 3)
}
val b3RaycastInformation = struct(Module.BULLET, "B3RaycastInformation", nativeName = "struct b3RaycastInformation") {
	AutoSize("m_rayHits")..int.member("m_numRayHits", "")
	b3RayHitInfo.p.buffer("m_rayHits", "")
}

private const val MAX_VR_ANALOG_AXIS = 5
private const val MAX_VR_BUTTONS = 64
val b3VRControllerEvent = struct(Module.BULLET, "B3VRControllerEvent", nativeName = "struct b3VRControllerEvent") {
	int.member("m_controllerId", "")  //valid for VR_CONTROLLER_MOVE_EVENT and VR_CONTROLLER_BUTTON_EVENT
	int.member("m_deviceType", "")
	int.member("m_numMoveEvents", "")
	int.member("m_numButtonEvents", "")

	float.array("m_pos", "", size = 4)  //valid for VR_CONTROLLER_MOVE_EVENT and VR_CONTROLLER_BUTTON_EVENT
	float.array("m_orn", "", size = 4)  //valid for VR_CONTROLLER_MOVE_EVENT and VR_CONTROLLER_BUTTON_EVENT

	float.member("m_analogAxis", "")                             //valid if VR_CONTROLLER_MOVE_EVENT
	float.array("m_auxAnalogAxis", "", size = MAX_VR_ANALOG_AXIS * 2)  //store x,y per axis, only valid if VR_CONTROLLER_MOVE_EVENT
	int.array("m_buttons", "", size = MAX_VR_BUTTONS)                  //valid if VR_CONTROLLER_BUTTON_EVENT, see b3VRButtonInfo
}

val b3VREventsData = struct(Module.BULLET, "B3VREventsData", nativeName = "struct b3VREventsData") {
	AutoSize("m_controllerEvents")..int.member("m_numControllerEvents", "")
	b3VRControllerEvent.p.buffer("m_controllerEvents", "")
}


val b3KeyboardEvent = struct(Module.BULLET, "B3KeyboardEvent", nativeName = "struct b3KeyboardEvent") {
	int.member("m_keyCode", "")   //ascii
	int.member("m_keyState", "")  // see b3VRButtonInfo
}

val b3KeyboardEventsData = struct(Module.BULLET, "B3KeyboardEventsData", nativeName = "struct b3KeyboardEventsData") {
	AutoSize("m_keyboardEvents")..int.member("m_numKeyboardEvents", "")
	b3KeyboardEvent.p.buffer("m_keyboardEvents", "")
}

val b3MouseEvent = struct(Module.BULLET, "B3MouseEvent", nativeName = "struct b3MouseEvent") {
	int.member("m_eventType", "")
	float.member("m_mousePosX", "")
	float.member("m_mousePosY", "")
	int.member("m_buttonIndex", "")
	int.member("m_buttonState", "")
}

val b3MouseEventsData = struct(Module.BULLET, "B3MouseEventsData", nativeName = "struct b3MouseEventsData") {
	AutoSize("m_mouseEvents")..int.member("m_numMouseEvents", "")
	b3MouseEvent.p.buffer("m_mouseEvents", "")
}